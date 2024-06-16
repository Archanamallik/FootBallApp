package com.example.livesoccer.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballdetails.R
import com.example.footballdetails.databinding.FragmentScheduleListBinding
import com.example.footballdetails.dataclass.Round
import com.example.footballdetails.dataclass.ScheduleModel


class ScheduleAdapter(private val schedule: ScheduleModel, private val teamId: Int)
    : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = FragmentScheduleListBinding.bind(view)
        fun bind(model: Round, teamId: Int) = with(binding){
            val awayIndex = if(model.fixtures[0].participants[0].id == teamId) 1
            else 0

            content.text = model.fixtures[0].participants[awayIndex].name

            time.text = model.fixtures[0].starting_at
                .substringAfter('-')
                .substringBeforeLast(':')

            Glide.with(itemView).load(model.fixtures[0].participants[awayIndex].image_path)
                .into(imageLeague)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_schedule_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = schedule.data?.get(0)?.rounds?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        schedule.data?.get(0)?.rounds?.get(position)?.let { holder.bind(it, teamId) }

    }
}