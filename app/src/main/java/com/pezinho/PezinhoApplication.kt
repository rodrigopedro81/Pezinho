package com.pezinho

import android.app.Application
import com.google.android.libraries.places.api.Places
import com.maps.LocationProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PezinhoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        LocationProvider.initialize(this)
        Places.initialize(
            applicationContext,
            "AIzaSyAIGOeWaLNCiT7Xk9v2AcSsNQstRrqK3c0"
        )
    }
}