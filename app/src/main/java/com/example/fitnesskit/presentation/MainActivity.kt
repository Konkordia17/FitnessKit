package com.example.fitnesskit.presentation

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.fitnesskit.R
import com.example.fitnesskit.presentation.broadcast_receiver.ConnectionBroadcastReceiver
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var broadcastReceiver: ConnectionBroadcastReceiver

    private val observerList = mutableListOf<BroadcastReceiverObserver>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        broadcastReceiver = ConnectionBroadcastReceiver { notifyObservers() }
        registerBroadcastReceiver()

        handleBottomNavigationClick()
    }

    fun registerObserver(observer: BroadcastReceiverObserver) {
        observerList.add(observer)
    }

    fun unregisterObserver(observer: BroadcastReceiverObserver) {
        observerList.remove(observer)
    }

    private fun notifyObservers() {
        for (observer in observerList) {
            observer.onBroadcastReceived()
        }
    }
    private fun registerBroadcastReceiver() {
        registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    private fun unregisterReceiver() {
        unregisterReceiver(broadcastReceiver)
    }

    private fun handleBottomNavigationClick() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_lessons -> {
                    true
                }

                R.id.navigation_request -> {
                    true
                }

                R.id.navigation_add -> {
                    true
                }

                R.id.navigation_chat -> {
                    true
                }

                R.id.navigation_more -> {
                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver()
    }
}

