package com.pezinho.di

import com.maps.GeoCodingRepositoryImpl
import com.network.retrofit.NetworkUtils
import com.network.service.GeoCodingService
import com.repositories.GeoCodingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val GEO_CODING_BASE_URL = "http://api.positionstack.com/v1/"

    @Provides
    @Singleton
    fun providesRetrofit(baseUrl: String): Retrofit = NetworkUtils.getRetrofitInstance(baseUrl)

    @Provides
    @Singleton
    fun providesGeoCodingService() =
        NetworkUtils.getApi(providesRetrofit(GEO_CODING_BASE_URL), GeoCodingService::class.java)

    @Provides
    @Singleton
    fun providesAddressRepository(): GeoCodingRepository =
        GeoCodingRepositoryImpl(providesGeoCodingService())
}