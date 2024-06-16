package com.example.footballdetails.network

import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.dataclass.TeamModel
import com.example.footballdetails.utils.API_TOKEN
import com.example.footballdetails.utils.TEAMBYID_URL

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamsApi {

    @GET(TEAMBYID_URL)
    suspend fun getTeamsById(
        @Path("countryId") countryId: Int,
        @Query("api_token") apiToken: String = API_TOKEN
    ): TeamModel
}