package com.example.footballdetails.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.dataclass.CountriesData
import com.example.footballdetails.dataclass.CountriesModel
import com.example.footballdetails.dataclass.PlayersData
import com.example.footballdetails.network.ContinentsApi
import com.example.footballdetails.network.CountriesApi
import com.example.footballdetails.network.PlayerApi
import com.example.footballdetails.paging.PagingSource
import com.example.moviesapp.paging.PlayersPagingSource
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val playerApi: PlayerApi){

   /* suspend fun getCountriesByID(continent_id:String): CountriesModel {
        return countriesApi.getCountries(continent_id)
    }*/

    fun getplayers(countryId: Int): LiveData<PagingData<PlayersData>> = Pager(
        config = PagingConfig(pageSize = 25, maxSize = 100),
        pagingSourceFactory = { PlayersPagingSource(playerApi,countryId) }
    ).liveData

}