package com.example.footballdetails.network

import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.dataclass.LeaguesModel
import com.example.footballdetails.utils.ALL_LEAGUES_URL
import com.example.footballdetails.utils.API_TOKEN
import com.example.footballdetails.utils.LEAGUES_URL

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LeagueApi {

    @GET(LEAGUES_URL)
    suspend fun getLeaguesByIdApi(
        @Path("countryId") countryId: Int,
        @Query("api_token") apiToken: String = API_TOKEN
    ): LeaguesModel

    @GET(ALL_LEAGUES_URL)
    suspend fun getAllLeaguesApi(
        @Query("api_token") apiToken: String = API_TOKEN
    ): LeaguesModel
}