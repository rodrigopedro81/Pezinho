package com.maps.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.entities.MarkerInfo
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

fun buildMapView(context: Context, startLocation: Pair<Double, Double>): MapView =
    MapView(context).then {
        setZoom(12.5)
        moveCameraTo(startLocation)
        setTileSource(TileSourceFactory.MAPNIK)
        onResume()
    }

fun MapView.removeMarkers() {
    overlayManager.clear()
}

fun MapView.placeMarker(marker: MarkerInfo) {
    overlayManager.add(marker.toMarker(this))
}

fun MapView.placeMarkers(markers: List<MarkerInfo>) {
    for (marker in markers) {
        placeMarker(marker)
    }
}

fun MapView.center(marker: MarkerInfo) {
    controller.setCenter(GeoPoint(marker.lat, marker.lng))
}

fun MapView.center(lat: Double, lng: Double) {
    controller.setCenter(GeoPoint(lat, lng))
}

fun MapView.moveCameraTo(currentLocation: Pair<Double, Double>) {
    controller.setCenter(currentLocation.toGeoPoint())
}


fun MapView.setZoom(zoom: Double) {
    controller.setZoom(zoom)
}

fun MarkerInfo.toMarker(mapView: MapView): Marker = Marker(mapView).apply {
    position = GeoPoint(lat, lng)
    title = title
    icon = ContextCompat.getDrawable(mapView.context, this@toMarker.icon)
}

fun <A, B> Pair<A, B>.toGeoPoint(): GeoPoint {
    return GeoPoint(first as Double, second as Double)
}

inline fun <T> T.then(block: T.() -> Unit): T {
    block(this)
    return this
}
