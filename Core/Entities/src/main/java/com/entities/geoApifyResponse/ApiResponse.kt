package com.entities.geoApifyResponse

import com.entities.AutoComplete

data class ApiResponse(
    val features: List<Feature>,
    val query: Query,
    val type: String
) {

    fun getFormattedAddresses(): List<String> = features.map { it.properties.formatted }

    fun getAutoCompletes(): List<AutoComplete> {
        val autoCompletes = features.map {
            AutoComplete(
                it.properties.formatted,
                it.geometry.coordinates[1],
                it.geometry.coordinates[0]
            )
        }
        return autoCompletes.ifEmpty { emptyList() }
    }

    fun getCoordinates(): Pair<Double, Double>? =
        features.first().geometry.coordinates.toPairCoordinates()
}

private fun <E> List<E>.toPairCoordinates(): Pair<Double, Double>? {
    val lat = this[0] as? Double ?: return null
    val lng = this[1] as? Double ?: return null
    return Pair(lat, lng)
}
