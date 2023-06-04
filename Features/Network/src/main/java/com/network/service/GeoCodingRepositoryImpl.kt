package com.network.service

import com.network.api.GeoCodingApi
import com.repositories.GeoCodingRepository

class GeoCodingRepositoryImpl(private val geoCodingApi: GeoCodingApi): GeoCodingRepository {

    override suspend fun getAddressByCoordinate(apiKey: String, lat: Double, lng: Double) {

    }

    override suspend fun getCoordinateByAddress(apiKey: String, address: String) {

    }

    override suspend fun getAutoCompletes(query: String): List<String> {
        return runCatching {
            val request = geoCodingApi.getAutoCompletes(query, apiKey)
            if (request.isSuccessful) {
                request.body()?.features?.map { it.properties.formatted } ?: emptyList()
            } else {
                throw Exception(request.message())
            }
        }.getOrDefault(emptyList())
    }

    companion object {
        private const val apiKey = "85eccc2434b64de3955e239f2222f2bf"
    }
}