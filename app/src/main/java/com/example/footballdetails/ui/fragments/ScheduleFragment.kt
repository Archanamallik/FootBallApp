package com.example.livesoccer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.footballdetails.R
import com.example.footballdetails.databinding.FragmentScheduleBinding
import com.example.footballdetails.viewmodels.FootballViewModel
import com.example.livesoccer.ui.adapters.ScheduleAdapter


class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding
    private val viewModel: FootballViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding){
           recyclerview.layoutManager = LinearLayoutManager(context)
            textSchedule.text = getString(R.string.fixtures, arguments?.getString("teamName"))
            Glide.with(this@ScheduleFragment).load(
                arguments?.getString("teamlogo")).into(imageCounty)
        }
        val teamId= arguments?.getInt("teamId")!!
       // val  countryName= arguments?.getString("countryName")
        viewModel.getFixtureByTeam(teamId)

        viewModel.scheduleLiveData.observe(viewLifecycleOwner){schedule ->
            binding.recyclerview.adapter = arguments?.getInt("teamId")?.let {
                    id -> ScheduleAdapter(schedule, id)
            }
        }
    }
}