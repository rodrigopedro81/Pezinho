package com.maps

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng


@SuppressLint("MissingPermission")
object LocationProvider {

    private const val DEFAULT_INTERVAL = 10000L
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var locationPermissionsGranted: Boolean = false
    private var currentLocationCallback: LocationCallback? = null
    private val locationRequestBuilder =
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, DEFAULT_INTERVAL)

    fun initialize(context: Context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    fun withCurrentLocation(onLocation: (latitude: Double, longitude: Double) -> Unit) {
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                location?.let {
                    onLocation.invoke(it.latitude, it.longitude)
                }
            }
    }

    fun setCurrentLocationCallback(
        interval: Long = DEFAULT_INTERVAL,
        onLocation: (latitude: Double, longitude: Double) -> Unit
    ) {
        requestRealTimeLocations(interval) { latitude, longitude ->
            onLocation.invoke(latitude, longitude)
        }
    }

    fun getCurrentLocation(): LatLng {
        with(fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null).result) {
            return LatLng(this.latitude, this.longitude)
        }
    }

    fun removeCurrentLocationCallback() {
        currentLocationCallback?.let { fusedLocationClient.removeLocationUpdates(it) }
    }

    private fun requestRealTimeLocations(
        interval: Long,
        onNewLocation: (latitude: Double, longitude: Double) -> Unit
    ) {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                for (coordinate in result.locations) {
                    onNewLocation.invoke(coordinate.latitude, coordinate.longitude)
                }
            }
        }
        val locationRequest = locationRequestBuilder.setIntervalMillis(interval).build()
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }
}