package com.repositories.network

import com.entities.AutoComplete

interface GeoCodingRepository {

    suspend fun getAddressByCoordinate(
        apiKey: String,
        lat: Double,
        lng: Double
    )

    suspend fun getCoordinateByAddress(
        address: String
    ): Pair<Double, Double>?

    suspend fun getAutoCompletes(
        query: String
    ): List<AutoComplete>
}