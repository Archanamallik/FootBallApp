package com.example.footballdetails.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.footballdetails.R

import com.example.footballdetails.databinding.FragmentContinentsListBinding
import com.example.footballdetails.databinding.FragmentLeaguesListBinding
import com.example.footballdetails.dataclass.ContinentsData
import com.example.footballdetails.dataclass.LeaguesData
import com.example.footballdetails.ui.listeners.ClickListener


class LeagueAdapter(private val leagues: List<LeaguesData>)
    : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = FragmentLeaguesListBinding.bind(view)

        fun bind(model: LeaguesData) = with(binding){
            content.text = model.name
            subType.text=model.sub_type
            time.text=model.last_played_at

            Glide.with(itemView).load(model.image_path)
                .into(imageLeague)



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_leagues_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = leagues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(leagues[position])
    }
}
