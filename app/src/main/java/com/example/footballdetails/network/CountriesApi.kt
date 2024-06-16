package com.example.footballdetails.network

import com.example.footballdetails.dataclass.CountriesModel
import com.example.footballdetails.utils.API_TOKEN
import com.example.footballdetails.utils.COUNTRY_API

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountriesApi {

    @GET(COUNTRY_API)
    suspend fun getCountries(

        @Query("page") page: Int,
        @Query("api_token") apiToken: String = API_TOKEN
    ): CountriesModel
}

