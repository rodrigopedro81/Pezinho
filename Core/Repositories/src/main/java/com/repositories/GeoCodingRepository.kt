package com.repositories

import com.entities.autoComplete.AutoCompleteResponse

interface GeoCodingRepository {

    suspend fun getAddressByCoordinate(
        apiKey: String,
        lat: Double,
        lng: Double
    )

    suspend fun getCoordinateByAddress(
        apiKey: String,
        address: String
    )

    suspend fun getAutoCompletes(
        query: String
    ): List<String>
}