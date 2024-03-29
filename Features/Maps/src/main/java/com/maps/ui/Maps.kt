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
import org.osmdroid.config.Configuration
import org.osmdroid.library.BuildConfig

@Composable
fun Maps(
    modifier: Modifier = Modifier,
    initialLocation: Pair<Double, Double> = Pair(-22.91, -43.29),
    barbersCoordinates: List<Pair<Double, Double>> = emptyList(),
) {
    var updatedLocation by remember { mutableStateOf(initialLocation) }
    val barbersMarkers = barbersCoordinates.getMarkers()
    val allMarkers = barbersMarkers + myMarker(updatedLocation.first, updatedLocation.second)
    DisposableEffect(Unit) {
        GPSClient.setCurrentLocationCallback { latitude, longitude ->
            updatedLocation = Pair(latitude, longitude)
        }
        onDispose {
            GPSClient.removeCurrentLocationCallback()
        }
    }
    MapsContent(modifier, updatedLocation, allMarkers)
}

private fun List<Pair<Double, Double>>.getMarkers(): List<MarkerInfo> {
    return map {
        MarkerInfo(
            lat = it.first,
            lng = it.second,
            title = "Barbeiro",
            snippet = "Barbeiro",
            icon = R.drawable.salon
        )
    }
}

@Composable
private fun MapsContent(
    modifier: Modifier,
    currentLocation: Pair<Double, Double>,
    markers: List<MarkerInfo>,
) {
    NoPreviewComponent(modifier) {
        Configuration.getInstance().userAgentValue = BuildConfig.LIBRARY_PACKAGE_NAME
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
                    removeMarkers()
                    placeMarkers(markers)
                    moveCameraTo(currentLocation)
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewMaps() {
    GPSClient.initialize(LocalContext.current)
    Maps(modifier = Modifier.fillMaxSize())
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
