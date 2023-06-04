package com.entities.geoApifyResponse

data class Rank(
    val confidence: Double,
    val confidence_city_level: Int,
    val confidence_street_level: Int,
    val importance: Double,
    val match_type: String,
    val popularity: Double
)