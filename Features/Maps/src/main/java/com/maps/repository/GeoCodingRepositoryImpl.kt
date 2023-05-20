package com.maps.repository

import com.network.service.GeoCodingService
import com.repositories.GeoCodingRepository
import javax.inject.Inject

class GeoCodingRepositoryImpl @Inject constructor(
    private val providesAddressService: GeoCodingService
) : GeoCodingRepository {


}