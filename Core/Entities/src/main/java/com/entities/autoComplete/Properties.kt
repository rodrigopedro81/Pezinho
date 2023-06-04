package com.entities.autoComplete

data class Properties(
    val address_line1: String,
    val address_line2: String,
    val category: String,
    val city: String,
    val country: String,
    val country_code: String,
    val county: String,
    val datasource: Datasource,
    val district: String,
    val formatted: String,
    val hamlet: String,
    val lat: Double,
    val lon: Double,
    val municipality: String,
    val name: String,
    val place_id: String,
    val postcode: String,
    val rank: Rank,
    val region: String,
    val result_type: String,
    val state: String,
    val state_code: String,
    val state_district: String,
    val suburb: String,
    val timezone: Timezone
)