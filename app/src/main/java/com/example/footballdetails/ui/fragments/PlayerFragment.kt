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
import com.example.footballdetails.databinding.FragmentPlayerBinding
import com.example.footballdetails.dataclass.CountriesData
import com.example.footballdetails.dataclass.PlayersData
import com.example.footballdetails.ui.adapters.CountryPagingAdapter
import com.example.footballdetails.ui.adapters.LeagueAdapter
import com.example.footballdetails.ui.adapters.PlayerPagingAdapter
import com.example.footballdetails.ui.listeners.ClickListener
import com.example.footballdetails.ui.listeners.CountryClickListener
import com.example.footballdetails.viewmodels.FootballViewModel

class PlayerFragment : Fragment(){


    private lateinit var binding : FragmentPlayerBinding
    private val footballViewModel : FootballViewModel  by activityViewModels()
    lateinit var adapter: PlayerPagingAdapter
     var filtertext:String=""
    var continentId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPlayerBinding.inflate(inflater)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          continentId= arguments?.getInt("countryId")!!
        val  countryName= arguments?.getString("countryName")
     //   binding.textContinent.text= getString(R.string.hello_countries_fragment, continentName)

        binding.textPlayer.text= getString(R.string.hello_player_fragment, countryName)



        binding.recyclerview.layoutManager = LinearLayoutManager(context)

        adapter = PlayerPagingAdapter()
        binding.recyclerview.adapter = adapter
        if (continentId != null) {
            footballViewModel.getPlayerList(continentId,filtertext).observe(viewLifecycleOwner, Observer {

                adapter.submitData(lifecycle, it)
            })
        }

        val menuHost: MenuHost = requireActivity()

        // Add menu items without using the Fragment Menu APIs
        // Note how we can tie the MenuProvider to the viewLifecycleOwner
        // and an optional Lifecycle.State (here, RESUMED) to indicate when
        // the menu should be visible
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.search_menu, menu)
               val searchView: SearchView =
                    menu.findItem(R.id.actionSearch).actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String): Boolean {


                        footballViewModel.getPlayerList(continentId,newText)
                            .observe(viewLifecycleOwner, Observer {
                            if(it!=null)
                            adapter.submitData(lifecycle, it)
                        })
                    //    filter(newText)
                        return false
                    }
                })


            }


            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.actionSearch -> {

                        // clearCompletedTasks()
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)





    }




}