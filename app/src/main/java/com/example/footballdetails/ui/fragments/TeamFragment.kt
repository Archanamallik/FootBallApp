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
import com.bumptech.glide.Glide
import com.example.footballdetails.R
import com.example.footballdetails.databinding.FragmentContinentsBinding
import com.example.footballdetails.databinding.FragmentLeaguesBinding
import com.example.footballdetails.databinding.FragmentTeamsBinding
import com.example.footballdetails.dataclass.ContinentsData
import com.example.footballdetails.dataclass.ContinentsModel
import com.example.footballdetails.dataclass.TeamsData
import com.example.footballdetails.ui.adapters.ContinentsAdapter
import com.example.footballdetails.ui.adapters.LeagueAdapter
import com.example.footballdetails.ui.adapters.TeamsAdapter
import com.example.footballdetails.ui.listeners.ClickListener
import com.example.footballdetails.utils.hasInternetConnection

import com.example.footballdetails.viewmodels.FootballViewModel


/**
 * A fragment representing a list of Items.
 */
class TeamFragment : Fragment(),ClickListener {

    private var columnCount = 1
    private lateinit var binding : FragmentTeamsBinding
    private val footballViewModel : FootballViewModel  by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTeamsBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val  countryId= arguments?.getInt("countryId")
        val  countryname= arguments?.getString("countryName")
        binding.textTeam.text=  getString(R.string.teams_of, countryname)
        Glide.with(view).load(arguments?.getString("imageUrl"))
            .into(binding.imageCounty)

        if(activity?.hasInternetConnection() == true){
            if (countryId != null && countryId>0) {
                footballViewModel.getTeamsList(countryId)
                binding.recyclerview.layoutManager = LinearLayoutManager(context)
                footballViewModel.teamLiveData.observe(viewLifecycleOwner){
                    if(it.data==null){
                        binding.noteam.visibility=View.VISIBLE
                        binding.recyclerview.visibility=View.INVISIBLE
                    }else {
                        binding.noteam.visibility=View.GONE
                        binding.recyclerview.visibility=View.VISIBLE
                        binding.recyclerview.adapter =
                            it.data?.let { it1 -> TeamsAdapter(it1, this) }
                    }
                }
            }else{
                binding.noteam.visibility=View.VISIBLE
                binding.recyclerview.visibility=View.INVISIBLE
            }

        }else{
            Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()

        }


    }

    override fun onItemClicked(model: Any) {
        if(activity?.hasInternetConnection() == false){
            Toast.makeText(activity, "No internet connection", Toast.LENGTH_SHORT).show()
        }else{
            val bundle = Bundle()
            bundle.putInt("teamId",(model as TeamsData).id)
            bundle.putString("teamName",(model as TeamsData).name)
            bundle.putString("teamlogo",(model as TeamsData).image_path)

            findNavController().navigate(R.id.action_teamFragment_to_scheduleFragment,bundle)
        }
    }

}