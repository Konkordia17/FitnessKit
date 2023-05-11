package com.example.fitnesskit.app

import android.app.Application
import com.example.fitnesskit.di.AppComponent
import com.example.fitnesskit.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}