package com.maps

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun GoogleMaps(
    modifier: Modifier = Modifier,
    currentLocation: Pair<Double, Double> = Pair(-22.91, -43.29),
    markers: List<Marker> = emptyList(),
) {
    val updatedLocation = remember { mutableStateOf(currentLocation) }
    DisposableEffect(Unit) {
        LocationProvider.setCurrentLocationCallback { latitude, longitude ->
            updatedLocation.value = Pair(latitude, longitude)
        }
        onDispose {
            LocationProvider.removeCurrentLocationCallback()
        }
    }
    Map(modifier, updatedLocation.value)
}

@Composable
private fun Map(
    modifier: Modifier,
    currentLocation: Pair<Double, Double>
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val map = buildMapView(context, currentLocation)
            map.addMarker(currentLocation)
            return@AndroidView map
        },
        update = { map ->
            map.removeMarkers()
            map.updateLocation(currentLocation)
            map.addMarker(currentLocation)
        }
    )
}

private fun MapView.removeMarkers() {
    overlayManager.clear()
}

private fun MapView.addMarker(currentLocation: Pair<Double, Double>) {
    val marker = Marker(this)
    with(marker) {
        snippet = "Hello world, bug 512 part 1"
        position = currentLocation.toGeoPoint()
        icon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_location_on_24)
    }
    controller.setCenter(marker.position)
    overlayManager.add(marker)
}

private fun buildMapView(context: Context, startLocation: Pair<Double, Double>): MapView =
    MapView(context).also {
        it.setZoom(12.5)
        it.updateLocation(startLocation)
        it.setTileSource(TileSourceFactory.MAPNIK)
        it.onResume()
    }


private fun <A, B> Pair<A, B>.toGeoPoint(): GeoPoint {
    return GeoPoint(first as Double, second as Double)
}

private fun MapView.updateLocation(currentLocation: Pair<Double, Double>) {
    controller.setCenter(currentLocation.toGeoPoint())
}

fun MapView.setZoom(zoom: Double) {
    controller.setZoom(zoom)
}

@Preview
@Composable
fun PreviewMaps() {
    LocationProvider.initialize(LocalContext.current)
    GoogleMaps(
//        barberList = emptyList(),
    )
}