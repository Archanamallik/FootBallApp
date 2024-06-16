package com.example.footballdetails.repository

import com.example.footballdetails.dataclass.ScheduleModel
import com.example.footballdetails.network.ScheduleApi
import javax.inject.Inject

class FixtureRepository @Inject constructor(private val scheduleApi: ScheduleApi) {

    suspend fun getScheduleById(teamId: Int):ScheduleModel {
        return scheduleApi.getScheduleById(teamId)
    }

}