package com.pezinho.di

import android.content.Context
import com.authentication.LoginRepositoryImpl
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.maps.PlacesHelper
import com.network.retrofit.NetworkUtils
import com.network.service.LoginService
import com.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://www.google.com"

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = NetworkUtils.getRetrofitInstance(BASE_URL)

    @Provides
    @Singleton
    fun providesLoginService() = NetworkUtils.getApi(providesRetrofit(), LoginService::class.java)

    @Provides
    @Singleton
    fun providesLoginRepository(): LoginRepository = LoginRepositoryImpl(providesLoginService())

    @Provides
    @Singleton
    fun providesPlacesClient(@ApplicationContext context: Context): PlacesClient =
        Places.createClient(context)

    @Provides
    @Singleton
    fun providesPlacesHelper(@ApplicationContext context: Context): PlacesHelper =
        PlacesHelper(providesPlacesClient(context))
}