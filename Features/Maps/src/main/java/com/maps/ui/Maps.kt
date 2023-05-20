package com.maps.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.designsystem.NoPreviewComponent
import com.entities.MarkerInfo
import com.maps.GPSClient
import com.maps.R
import com.maps.utils.buildMapView
import com.maps.utils.moveCameraTo
import com.maps.utils.placeMarkers
import com.maps.utils.removeMarkers
import com.maps.utils.then

@Composable
fun OpenSourceMaps(
    modifier: Modifier = Modifier,
    initialLocation: Pair<Double, Double> = Pair(-22.91, -43.29),
    barbersMarkers: List<MarkerInfo> = emptyList(),
) {
    var updatedLocation by remember { mutableStateOf(initialLocation) }
    val allMarkers = barbersMarkers + myMarker(updatedLocation.first, updatedLocation.second)
    DisposableEffect(Unit) {
        GPSClient.setCurrentLocationCallback { latitude, longitude ->
            updatedLocation = Pair(latitude, longitude)
        }
        onDispose {
            GPSClient.removeCurrentLocationCallback()
        }
    }
    Map(modifier, updatedLocation, allMarkers)
}

@Composable
private fun Map(
    modifier: Modifier,
    currentLocation: Pair<Double, Double>,
    markers: List<MarkerInfo>,
) {
    NoPreviewComponent(modifier) {
        AndroidView(
            modifier = modifier,
            factory = { context ->
                return@AndroidView buildMapView(context, currentLocation).then {
                    placeMarkers(markers)
                    moveCameraTo(currentLocation)
                }
            },
            update = { map ->
                map.run {
                    moveCameraTo(currentLocation)
                    removeMarkers()
                    placeMarkers(markers)
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewMaps() {
    GPSClient.initialize(LocalContext.current)
    OpenSourceMaps(modifier = Modifier.fillMaxSize())
}

fun myMarker(lat: Double, lng: Double): MarkerInfo {
    return MarkerInfo(
        lat = lat,
        lng = lng,
        title = "Minha localização",
        snippet = "Você está aqui",
        icon = R.drawable.ic_baseline_location_on_24
    )
}
