package com.example.fitnesskit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.fitnesskit.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        handleBottomNavigationClick()
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
}

