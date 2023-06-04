package com.network.api

import com.entities.autoComplete.AutoCompleteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoCodingApi {

    @GET("autocomplete")
    suspend fun getAutoCompletes(
        @Query("text") query: String,
        @Query("apiKey") apiKey: String
    ): Response<AutoCompleteResponse>
}