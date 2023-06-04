package com.network.service

import com.entities.AutoComplete
import com.network.api.GeoCodingApi
import com.repositories.network.GeoCodingRepository

class GeoCodingRepositoryImpl(private val geoCodingApi: GeoCodingApi) : GeoCodingRepository {

    override suspend fun getAddressByCoordinate(apiKey: String, lat: Double, lng: Double) {

    }

    override suspend fun getCoordinateByAddress(address: String): Pair<Double, Double>? {
        return runCatching {
            val request = geoCodingApi.getCoordinateByAddress(address, apiKey)
            if (request.isSuccessful) {
                request.body()?.getCoordinates()
            } else {
                throw Exception(request.message())
            }
        }.getOrNull()
    }

    override suspend fun getAutoCompletes(query: String): List<AutoComplete> {
        return runCatching {
            val request = geoCodingApi.getAutoCompletes(query, apiKey)
            if (request.isSuccessful) {
                request.body()?.getAutoCompletes() ?: emptyList()
            } else {
                throw Exception(request.message())
            }
        }.getOrDefault(emptyList())
    }

    companion object {
        private const val apiKey = "85eccc2434b64de3955e239f2222f2bf"
    }
}