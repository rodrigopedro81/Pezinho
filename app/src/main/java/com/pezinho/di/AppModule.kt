package com.pezinho.di

import com.authentication.AuthenticatorImpl
import com.database.FirestoreRepositoryImpl
import com.network.api.GeoCodingApi
import com.network.retrofit.NetworkUtils
import com.network.service.GeoCodingRepositoryImpl
import com.repositories.Authenticator
import com.repositories.FirestoreRepository
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

    private const val GEO_CODING_BASE_URL = "https://api.geoapify.com/v1/geocode/"

    @Provides
    @Singleton
    fun providesRetrofit(baseUrl: String): Retrofit = NetworkUtils.getRetrofitInstance(baseUrl)

    @Provides
    @Singleton
    fun providesGeoCodingApi() =
        NetworkUtils.getApi(providesRetrofit(GEO_CODING_BASE_URL), GeoCodingApi::class.java)

    @Provides
    @Singleton
    fun providesGeoCodingRepository(): GeoCodingRepository =
        GeoCodingRepositoryImpl(providesGeoCodingApi())

    @Provides
    @Singleton
    fun providesFirestoreRepository(): FirestoreRepository = FirestoreRepositoryImpl()

    @Provides
    @Singleton
    fun providesAuthenticator(): Authenticator = AuthenticatorImpl(providesFirestoreRepository())
}