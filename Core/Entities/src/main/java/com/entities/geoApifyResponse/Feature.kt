package com.entities.geoApifyResponse

data class Feature(
    val bbox: List<Double>,
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)