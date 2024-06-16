package com.example.footballdetails.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.footballdetails.R

import com.example.footballdetails.databinding.FragmentContinentsListBinding
import com.example.footballdetails.dataclass.ContinentsData
import com.example.footballdetails.ui.listeners.ClickListener


class ContinentsAdapter(  private val continents: List<ContinentsData>,private val listener: ClickListener)
    : RecyclerView.Adapter<ContinentsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: FragmentContinentsListBinding): RecyclerView.ViewHolder(binding.root) {
       // private val binding = FragmentContinentsListBinding.bind(view)

        fun bind(model: ContinentsData, listener: ClickListener) = with(binding){
            content.text = model.name


            itemView.setOnClickListener {
                listener.onItemClicked(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {


        val binding = FragmentContinentsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = continents.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(continents[position], listener)
    }
}
