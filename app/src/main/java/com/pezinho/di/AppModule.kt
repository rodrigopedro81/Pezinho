package com.pezinho.di

import com.network.service.LoginService
import com.authentication.LoginRepositoryImpl
import com.network.retrofit.NetworkUtils
import com.repositories.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://www.google.com"

//    @Provides
//    @Singleton
//    fun provideAuthenticator() : FirebaseAuthenticator =
//        FirebaseAuthenticatorImpl()
//

    @Provides
    @Singleton
    fun providesRetrofit() = NetworkUtils.getRetrofitInstance(BASE_URL)

    @Provides
    @Singleton
    fun providesLoginRepository() : LoginRepository = LoginRepositoryImpl(providesLoginService())

    @Provides
    @Singleton
    fun providesLoginService() = NetworkUtils.getApi(providesRetrofit(), LoginService::class.java)
}