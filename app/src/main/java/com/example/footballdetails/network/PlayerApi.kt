package com.example.footballdetails.network

import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.dataclass.PlayersModel
import com.example.footballdetails.dataclass.TeamModel
import com.example.footballdetails.utils.API_TOKEN
import com.example.footballdetails.utils.PLAYERBYID_URL

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerApi {

    @GET(PLAYERBYID_URL)
    suspend fun getPlayerById(
        @Path("countryId") countryId: Int,
        @Query("page") page: Int,
        @Query("api_token") apiToken: String = API_TOKEN
    ): PlayersModel
}