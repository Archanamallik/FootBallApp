package com.example.footballdetails.repository

import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.dataclass.LeaguesModel
import com.example.footballdetails.network.ContinentsApi
import com.example.footballdetails.network.LeagueApi
import javax.inject.Inject

class LeagueRepository @Inject constructor(private val leagueApi: LeagueApi){

    suspend fun getLeagues( countryId :Int): LeaguesModel {
        return leagueApi.getLeaguesByIdApi( countryId)
    }
    suspend fun getAllLeagues(): LeaguesModel {
        return leagueApi.getAllLeaguesApi()
    }

}