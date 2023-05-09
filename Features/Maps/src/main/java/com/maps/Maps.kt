package com.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.designsystem.VerticalSpacer
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

val permissions = arrayOf(
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION,
)

@Composable
fun GoogleMaps(modifier: Modifier = Modifier) {
    var currentLocation by remember {
        mutableStateOf(LatLng(0.toDouble(), 0.toDouble()))
    }
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                // Update UI with location data
                currentLocation = LatLng(lo.latitude, lo.longitude)
            }
        }
    }
    val permissionRequester = permissionRequester {
        Log.d("Teste", "Permissions granted = $it")
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation, 12f)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(bottom = 12.dp)
    ) {
        GoogleMap(
            modifier = modifier
                .height(400.dp)
                .fillMaxWidth(),
            cameraPositionState = cameraPositionState,
        )
        VerticalSpacer(dp = 20.dp)
        Button(onClick = {
            permissionRequester.launch(permissions)
            Log.d(
                "Teste",
                "Latitude e Longitude ${currentLocation.latitude} --- ${currentLocation.longitude}"
            )
            cameraPositionState.move(CameraUpdateFactory.newLatLng(currentLocation))
        }) {
            Text(text = if (currentLocation.latitude == 0.0) "Click me to ask for permissions" else currentLocation.toString())
            fusedLocationClient.requestLocationUpdates(locationCallback)
        }
    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun permissionRequester(
    onResult: (Boolean) -> Unit
) = rememberLauncherForActivityResult(
    ActivityResultContracts.RequestMultiplePermissions()
) { permissionsMap ->
    val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
    onResult.invoke(areGranted)
}

@SuppressLint("MissingPermission")
fun FusedLocationProviderClient.requestLocationUpdates(locationCallback: LocationCallback) {
    val locationRequest = LocationRequest.create().apply {
        interval = 10000
        fastestInterval = 5000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    requestLocationUpdates(
        locationRequest,
        locationCallback,
        Looper.getMainLooper()
    )
}

@Preview
@Composable
fun PreviewGoogleMaps() {
    GoogleMaps()
}