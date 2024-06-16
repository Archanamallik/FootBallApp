package com.example.footballdetails.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.dataclass.CountriesData
import com.example.footballdetails.dataclass.CountriesModel
import com.example.footballdetails.network.ContinentsApi
import com.example.footballdetails.network.CountriesApi
import com.example.footballdetails.paging.PagingSource
import javax.inject.Inject

class CountryRepository @Inject constructor(private val countriesApi: CountriesApi){

   /* suspend fun getCountriesByID(continent_id:String): CountriesModel {
        return countriesApi.getCountries(continent_id)
    }*/

    fun getCountries(): LiveData<PagingData<CountriesData>> = Pager(
        config = PagingConfig(pageSize = 25, maxSize = 100),
        pagingSourceFactory = { PagingSource(countriesApi) }
    ).liveData

}