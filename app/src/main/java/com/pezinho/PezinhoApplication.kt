package com.pezinho

import android.app.Application
import com.maps.LocationProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PezinhoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        LocationProvider.initialize(this)
    }
}