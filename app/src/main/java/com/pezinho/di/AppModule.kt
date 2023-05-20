package com.pezinho.di

import com.authentication.LoginRepositoryImpl
import com.network.retrofit.NetworkUtils
import com.network.service.LoginService
import com.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}