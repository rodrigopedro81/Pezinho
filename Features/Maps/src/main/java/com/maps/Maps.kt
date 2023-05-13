package com.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.entities.Barber
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun GoogleMaps(
    modifier: Modifier = Modifier,
    lastLocation: LatLng = LatLng(0.0, 0.0),
    barberList: List<Barber> = emptyList(),
    onMarkerClick: (LatLng) -> Unit = {},
    mapProperties: MapProperties = MapProperties(),
    mapUiSettings: MapUiSettings = MapUiSettings()
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lastLocation, 15f)
    }
    DisposableEffect(Unit) {
        LocationProvider.setCurrentLocationCallback { latitude, longitude ->
            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                LatLng(latitude, longitude),
                15f
            )
        }
        onDispose {
            LocationProvider.removeCurrentLocationCallback()
        }
    }
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = mapUiSettings
    ) {
        for (barber in barberList) {
            Marker(
                state = rememberMarkerState(
                    position = LatLng(
                        barber.latitude,
                        barber.longitude
                    )
                ),
                title = barber.title,
                snippet = barber.snippet,
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)
            )
        }
    }
}

@Preview
@Composable
fun PreviewGoogleMaps() {
    LocationProvider.initialize(LocalContext.current)
    GoogleMaps(
        barberList = emptyList(),
    )
}