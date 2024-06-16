package com.example.footballdetails.network

import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.utils.API_TOKEN
import com.example.footballdetails.utils.CONTINENT_API

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ContinentsApi {

    @GET(CONTINENT_API)
    suspend fun getContinents(
        @Query("api_token") apiToken: String = API_TOKEN
    ): ContinentsModel
}