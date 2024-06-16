package com.example.footballdetails.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballdetails.R
import com.example.footballdetails.databinding.FragmentCountriesBinding
import com.example.footballdetails.dataclass.CountriesData
import com.example.footballdetails.dataclass.TeamsData
import com.example.footballdetails.ui.adapters.CountryPagingAdapter
import com.example.footballdetails.ui.adapters.LeagueAdapter
import com.example.footballdetails.ui.listeners.CountryClickListener
import com.example.footballdetails.utils.hasInternetConnection
import com.example.footballdetails.viewmodels.FootballViewModel


class CountriesFragment : Fragment(), CountryClickListener {


    private lateinit var binding : FragmentCountriesBinding
    private val footballViewModel : FootballViewModel  by activityViewModels()
    lateinit var adapter: CountryPagingAdapter
     var filtertext:String=""
    var continentId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCountriesBinding.inflate(inflater)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          continentId= arguments?.getInt("continentId")!!
        val  continentName= arguments?.getString("continentName")
        binding.textContinent.text= getString(R.string.hello_countries_fragment, continentName)


      //  footballViewModel.getLeaguesList(0)
       /* var adResults : List<Int>?
        footballViewModel.leagueLiveData.observe(viewLifecycleOwner){
            adResults=it.data?.map { it1 -> it1.id }

        }*/
        binding.recyclerview.layoutManager = LinearLayoutManager(context)

        adapter = CountryPagingAdapter(this)
        binding.recyclerview.adapter = adapter
        if (continentId != null) {
            footballViewModel.getCountryList(continentId,filtertext).observe(viewLifecycleOwner, Observer {

                adapter.submitData(lifecycle, it)
            })
        }




    }

    override fun onItemClicked(model: CountriesData, view: View) {
        footballViewModel.getTeamsList((model as CountriesData).id)
        when(view.id){
            R.id.leagues->{
                if(activity?.hasInternetConnection() == false){
                    Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()
                }else{
                    val bundle = Bundle()
                    bundle.putInt("countryId",(model as CountriesData).id)
                    bundle.putString("countryName",(model as CountriesData).name)
                    bundle.putString("imageUrl",(model as CountriesData).image_path)
                    findNavController().navigate(R.id.action_countriesFragment_to_leaguesFragment,bundle)

                }
            }
            R.id.teams->{
                val bundle = Bundle()
                bundle.putInt("countryId",(model as CountriesData).id)
                bundle.putString("countryName",(model as CountriesData).name)
                bundle.putString("imageUrl",(model as CountriesData).image_path)
                footballViewModel.getTeamsList((model as CountriesData).id)
                /*  footballViewModel.getTeamsList((model as CountriesData).id)
                  var teamlist=ArrayList<TeamsData>()
                  footballViewModel.teamLiveData.observe(viewLifecycleOwner){
                      teamlist.clear()
                      teamlist.addAll( it.data)
                      }
                  bundle.putParcelableArrayList("list",teamlist)*/
                findNavController().navigate(R.id.action_countriesFragment_to_teamFragment,bundle)

            }
            R.id.players->{
                val bundle = Bundle()
                bundle.putInt("countryId",(model as CountriesData).id)
                bundle.putString("countryName",(model as CountriesData).name)
                bundle.putString("imageUrl",(model as CountriesData).image_path)
                findNavController().navigate(R.id.action_countriesFragment_to_playerFragment,bundle)
            }

        }

    }

}