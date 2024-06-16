package com.example.footballdetails.repository

import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.network.ContinentsApi
import javax.inject.Inject

class ContinentRepository @Inject constructor(private val continentsApi: ContinentsApi){

    suspend fun getContinents(): ContinentsModel {
        return continentsApi.getContinents()
    }


}