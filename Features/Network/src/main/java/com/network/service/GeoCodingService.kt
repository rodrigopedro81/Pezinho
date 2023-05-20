package com.network.service

interface GeoCodingService {

    suspend fun getAddress(apiKey: String, lat: Double, lng: Double)
}