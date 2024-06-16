package com.example.footballdetails.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.example.footballdetails.dataclass.ContinentsData
import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.dataclass.CountriesData
import com.example.footballdetails.dataclass.CountriesModel
import com.example.footballdetails.dataclass.LeaguesModel
import com.example.footballdetails.dataclass.PlayersData
import com.example.footballdetails.dataclass.PlayersModel
import com.example.footballdetails.dataclass.ScheduleModel
import com.example.footballdetails.dataclass.TeamModel
import com.example.footballdetails.repository.ContinentRepository
import com.example.footballdetails.repository.CountryRepository
import com.example.footballdetails.repository.FixtureRepository
import com.example.footballdetails.repository.LeagueRepository
import com.example.footballdetails.repository.PlayerRepository
import com.example.footballdetails.repository.TeamsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FootballViewModel @Inject constructor(
    private val continentRepository: ContinentRepository,
    private val countryRepository: CountryRepository,
    private val leagueRepository: LeagueRepository,
    private val teamsRepository: TeamsRepository,
    private val playerRepository: PlayerRepository,
    private val fixtureRepository: FixtureRepository

) :ViewModel(){
        private val mutableContinentList = MutableLiveData<ContinentsModel>()
        val continentLiveData: LiveData<ContinentsModel> = mutableContinentList

    private val mutableLeagueList = MutableLiveData<LeaguesModel>()
    val leagueLiveData: LiveData<LeaguesModel> = mutableLeagueList
    private val mutablecountry = MutableLiveData<PagingData<CountriesData>>()
    var countryData: LiveData<PagingData<CountriesData>> = mutablecountry
    private val mutableAllLeagues = MutableLiveData<LeaguesModel>()
    val allLeagueLiveData: LiveData<LeaguesModel> = mutableAllLeagues
    private val mutableteamsList = MutableLiveData<TeamModel>()
    val teamLiveData: LiveData<TeamModel> = mutableteamsList
    private val mutableplayersList = MutableLiveData<PagingData<PlayersData>>()
    var playersData: LiveData<PagingData<PlayersData>> = mutableplayersList
    private val mutableScheduleLiveData = MutableLiveData<ScheduleModel>()
    val scheduleLiveData: LiveData<ScheduleModel> = mutableScheduleLiveData

fun getCountryList( continentId:Int, filtertxt:String?): LiveData<PagingData<CountriesData>> {

    val data= countryRepository.getCountries()
    if (filtertxt != null && filtertxt.trim().isNotEmpty()) {

        countryData= data.map { pagingData ->
            pagingData.filter {
                it.continent_id == continentId
                        && (it.name.lowercase().contains(filtertxt.lowercase()))
            }
            //.map { user -> UiModel.UserModel(user) }
        }.cachedIn(viewModelScope)
    }else{
        countryData= data.map {pagingData ->
            pagingData.filter {
                it.continent_id==continentId

            }
            //.map { user -> UiModel.UserModel(user) }
        }.cachedIn(viewModelScope)
    }
    return countryData


}
    fun getPlayerList( countryId:Int, filtertxt:String?): LiveData<PagingData<PlayersData>> {

        val data= playerRepository.getplayers(countryId)

        if (filtertxt != null && filtertxt.trim().isNotEmpty()) {
          //  playersData=  data.map { it-> it.filter { it.name.lowercase().contains(filtertxt.lowercase()) } }
            playersData= data.map { pagingData ->
                pagingData.filter {
                    (it.name.lowercase().contains(filtertxt.lowercase()))
                }

            }.cachedIn(viewModelScope)
        }else{
            playersData= data

            .cachedIn(viewModelScope)
        }
        return playersData


    }
    fun getTeamsList(countryId :Int) {
        viewModelScope.launch {
            val teamlist = withContext(Dispatchers.IO) {
                    teamsRepository.getTeams(countryId)
            }

            mutableteamsList.value = teamlist
        }
    }

    fun getContinentsList() {
        viewModelScope.launch {
            val continentlist = withContext(Dispatchers.IO) {
                continentRepository.getContinents()
            }
            mutableContinentList.value = continentlist
        }
    }
    fun getLeaguesList(countryId :Int) {
        viewModelScope.launch {
            val leaguelist = withContext(Dispatchers.IO) {
                if(countryId==0)
                    leagueRepository.getAllLeagues()
                else
                leagueRepository.getLeagues(countryId)
            }

            mutableLeagueList.value = leaguelist
        }
    }

    fun getFixtureByTeam(teamId: Int) {
        viewModelScope.launch {
            val teamSchedule = withContext(Dispatchers.IO) {
                fixtureRepository.getScheduleById(teamId)
            }
            mutableScheduleLiveData.value = teamSchedule
        }
    }

}