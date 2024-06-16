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
import com.example.footballdetails.dataclass.TeamsData
import com.example.footballdetails.ui.listeners.ClickListener


class TeamsAdapter(private val teams: List<TeamsData>, private val listener: ClickListener)
    : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = FragmentLeaguesListBinding.bind(view)

        fun bind(model: TeamsData, listener: ClickListener) = with(binding){
            content.text = model.name
            subType.text=model.type
            time.text=model.last_played_at

            Glide.with(itemView).load(model.image_path)
                .into(imageLeague)


            itemView.setOnClickListener {
                listener.onItemClicked(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_leagues_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(teams[position], listener)
    }
}
