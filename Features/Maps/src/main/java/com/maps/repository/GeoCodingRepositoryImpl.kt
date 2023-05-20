package com.maps.repository

import com.network.service.GeoCodingService
import com.repositories.GeoCodingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GeoCodingRepositoryImpl @Inject constructor(
    private val geoCodingService: GeoCodingService
) : GeoCodingRepository {

    private val dispatcher = Dispatchers.IO

    suspend fun getAddress(apiKey: String, lat: Double, lng: Double) {
        return withContext(dispatcher) {
            geoCodingService.getAddress(apiKey, lat, lng)
        }
    }
}