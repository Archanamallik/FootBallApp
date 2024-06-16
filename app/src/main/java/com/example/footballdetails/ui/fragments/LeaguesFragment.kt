package com.example.footballdetails.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.footballdetails.R
import com.example.footballdetails.databinding.FragmentContinentsBinding
import com.example.footballdetails.databinding.FragmentLeaguesBinding
import com.example.footballdetails.dataclass.ContinentsData
import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.ui.adapters.ContinentsAdapter
import com.example.footballdetails.ui.adapters.LeagueAdapter
import com.example.footballdetails.ui.listeners.ClickListener
import com.example.footballdetails.utils.hasInternetConnection

import com.example.footballdetails.viewmodels.FootballViewModel

/**
 * A fragment representing a list of Items.
 */
class LeaguesFragment : Fragment() {

    private var columnCount = 1
    private lateinit var binding : FragmentLeaguesBinding
    private val footballViewModel : FootballViewModel  by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLeaguesBinding.inflate(inflater)
        return binding.root

        /*
                val view = inflater.inflate(R.layout.fragment_continents, container, false)
              //  RecyclerView recyclerview= view.re
                // Set the adapter
                if (view is RecyclerView) {
                    with(view) {
                        layoutManager = when {
                            columnCount <= 1 -> LinearLayoutManager(context)
                            else -> GridLayoutManager(context, columnCount)
                        }
                        adapter = MyContinentsRecyclerViewAdapter(PlaceholderContent.ITEMS)
                    }
                }
                return view*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val  countryId= arguments?.getInt("countryId")
        if(activity?.hasInternetConnection() == true){
            if (countryId != null && countryId>0) {
                footballViewModel.getLeaguesList(countryId)
                binding.recyclerview.layoutManager = LinearLayoutManager(context)
                footballViewModel.leagueLiveData.observe(viewLifecycleOwner){
                    if(it.data==null){
                       binding.noleagues.visibility=View.VISIBLE
                        binding.recyclerview.visibility=View.INVISIBLE
                    }else {
                        binding.noleagues.visibility=View.GONE
                        binding.recyclerview.visibility=View.VISIBLE
                        binding.recyclerview.adapter =
                            it.data?.let { it1 -> LeagueAdapter(it1) }
                    }
                }
            }else{
                footballViewModel.getLeaguesList(0)
                binding.recyclerview.layoutManager = LinearLayoutManager(context)
                footballViewModel.leagueLiveData.observe(viewLifecycleOwner){
                    binding.recyclerview.adapter = it.data?.let { it1 -> LeagueAdapter(it1) }
                }
            }

        }else{
            Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()

        }

        /*  binding.textContinent.setOnClickListener {
              if(activity?.hasInternetConnection() == false){
                  Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()
              }else{
                  val bundle = Bundle()
                  bundle.putString("country", "Denmark")
                  findNavController().navigate(R.id.action_continentsFragment_to_countriesFragment,bundle)
              }
          }*/




    }


}