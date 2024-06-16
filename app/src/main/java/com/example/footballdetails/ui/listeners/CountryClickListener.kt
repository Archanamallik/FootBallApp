package com.example.footballdetails.ui.listeners

import android.view.View
import com.example.footballdetails.dataclass.ContinentsData
import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.dataclass.CountriesData

interface CountryClickListener {
    fun onItemClicked(model: CountriesData,view: View)

}