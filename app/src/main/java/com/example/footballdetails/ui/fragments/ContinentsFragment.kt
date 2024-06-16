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
import com.example.footballdetails.dataclass.ContinentsData
import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.ui.adapters.ContinentsAdapter
import com.example.footballdetails.ui.listeners.ClickListener
import com.example.footballdetails.utils.hasInternetConnection

import com.example.footballdetails.viewmodels.FootballViewModel

/**
 * A fragment representing a list of Items.
 */
class ContinentsFragment : Fragment(),ClickListener {

    private var columnCount = 1
    private lateinit var binding : FragmentContinentsBinding
    private val footballViewModel : FootballViewModel  by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       /* arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            binding = FragmentContinentsBinding.inflate(inflater)
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

        if(activity?.hasInternetConnection() == true){
            footballViewModel.getContinentsList()

        }else{
            Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.INVISIBLE
            binding.tryAgainBtn.visibility = View.VISIBLE
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        footballViewModel.continentLiveData.observe(viewLifecycleOwner){
            binding.recyclerview.visibility=View.VISIBLE
            binding.progressBar.visibility=View.GONE
            binding.recyclerview.adapter = it.data?.let { it1 -> ContinentsAdapter(it1,this) }
        }
        binding.tryAgainBtn.setOnClickListener {
            if(activity?.hasInternetConnection() == true){
                footballViewModel.getContinentsList()
                binding.tryAgainBtn.visibility = View.INVISIBLE
                binding.progressBar.visibility = View.VISIBLE
            }else{
                Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()
            }
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

    override fun onItemClicked(model: Any) {
        if(activity?.hasInternetConnection() == false){
            Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()
        }else{
            val bundle = Bundle()
            bundle.putInt("continentId",(model as ContinentsData).id)
            bundle.putString("continentName",(model as ContinentsData).name)
            findNavController().navigate(R.id.action_continentsFragment_to_countriesFragment,bundle)
        }
    }

}