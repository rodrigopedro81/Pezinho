package com.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.entities.MarkerInfo

@Composable
fun OpenSourceMaps(
    modifier: Modifier = Modifier,
    currentLocation: Pair<Double, Double> = Pair(-22.91, -43.29),
    barbersMarkers: List<MarkerInfo> = emptyList(),
) {
    val updatedLocation = remember { mutableStateOf(currentLocation) }
    val allMarkers = barbersMarkers + myMarker(currentLocation.first, currentLocation.second)
    DisposableEffect(Unit) {
        LocationProvider.setCurrentLocationCallback { latitude, longitude ->
            updatedLocation.value = Pair(latitude, longitude)
        }
        onDispose {
            LocationProvider.removeCurrentLocationCallback()
        }
    }
    Map(modifier, updatedLocation.value, allMarkers)
}

@Composable
private fun Map(
    modifier: Modifier,
    currentLocation: Pair<Double, Double>,
    markers: List<MarkerInfo>,
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val map = buildMapView(context, currentLocation)
            map.placeMarkers(markers)
            return@AndroidView map
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

@Preview
@Composable
fun PreviewMaps() {
    LocationProvider.initialize(LocalContext.current)
    OpenSourceMaps(
//        barberList = emptyList(),
    )
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
