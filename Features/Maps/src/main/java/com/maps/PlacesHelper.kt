package com.maps

import android.util.Log
import com.entities.AutoComplete
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import javax.inject.Inject

class PlacesHelper @Inject constructor(private val placesClient: PlacesClient) {

    private val requestBuilder = FindAutocompletePredictionsRequest.builder()

    fun findAutoCompletePredictions(
        query: String,
        onSuccess: (List<AutoComplete>) -> Unit
    ) {
        placesClient.findAutocompletePredictions(query.toPlaceRequest())
            .addOnSuccessListener {
                val autoCompletions = it.autocompletePredictions.map { prediction ->
                    AutoComplete(
                        prediction.getPrimaryText(null).toString(),
                        prediction.placeId
                    )
                }.toMutableList()
                onSuccess(autoCompletions)
            }.addOnFailureListener {
                Log.d("Teste", " ${it.message}")
            }
    }

    fun getAddress() {
//        val address = geoCoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
//        text = address?.get(0)?.getAddressLine(0).toString()
    }

    private fun String.toPlaceRequest(): FindAutocompletePredictionsRequest =
        requestBuilder.setQuery(this).build()
}