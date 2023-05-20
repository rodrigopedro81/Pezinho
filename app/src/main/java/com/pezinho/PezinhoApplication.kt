package com.pezinho

import android.app.Application
import com.maps.GPSClient
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PezinhoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        GPSClient.initialize(this)
    }
}