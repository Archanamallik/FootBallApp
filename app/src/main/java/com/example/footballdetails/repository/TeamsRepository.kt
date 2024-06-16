package com.example.footballdetails.repository

import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.dataclass.TeamModel
import com.example.footballdetails.network.ContinentsApi
import com.example.footballdetails.network.TeamsApi
import javax.inject.Inject

class TeamsRepository @Inject constructor(private val teamsApi: TeamsApi){

    suspend fun getTeams(countryId:Int): TeamModel {
        return teamsApi.getTeamsById(countryId)
    }


}