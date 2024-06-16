package com.example.footballdetails.network

import com.example.footballdetails.dataclass.PlayersModel
import com.example.footballdetails.dataclass.ScheduleModel
import com.example.footballdetails.utils.API_TOKEN
import com.example.footballdetails.utils.FIXTUREBYID_URL

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApi {

    @GET(FIXTUREBYID_URL)
    suspend fun getScheduleById(
        @Path("teamId") teamId: Int,
        @Query("api_token") apiToken: String = API_TOKEN
    ): ScheduleModel
}