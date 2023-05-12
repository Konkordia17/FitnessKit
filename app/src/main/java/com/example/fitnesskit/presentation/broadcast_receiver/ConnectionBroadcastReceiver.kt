package com.example.fitnesskit.presentation.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.example.fitnesskit.R

class ConnectionBroadcastReceiver(private val onGetMessage: () -> Unit) : BroadcastReceiver() {
    private lateinit var dialog: AlertDialog


    override fun onReceive(context: Context, intent: Intent) {
        doIfNetworkConnectionIsChanged(context)
    }

    private fun doIfNetworkConnectionIsChanged(context: Context) {
        try {
            if (!isNetworkAvailable(context)) {
                showDialog(context)
            } else {
                if (dialog.isShowing) {
                    dialog.dismiss()
                }
                onGetMessage.invoke()
            }
        } catch (t: Throwable) {
            Log.d("TAG", String.format(context.resources.getString(R.string.register_error), "$t"))
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }

    private fun showDialog(context: Context) {
        val builder = AlertDialog.Builder(context)
            .setTitle(context.resources.getString(R.string.no_internet_connection))
            .setMessage(context.resources.getString(R.string.go_to_settings_title))
            .setPositiveButton(
                context.resources.getString(R.string.go_to_settings)
            ) { dialog, _ ->
                val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                context.startActivity(intent)
                dialog.dismiss()
            }
            .setNegativeButton(
                context.resources.getString(R.string.cancel)
            ) { dialog, _ -> dialog.dismiss() }
        dialog = builder.create()
        dialog.show()
    }
}