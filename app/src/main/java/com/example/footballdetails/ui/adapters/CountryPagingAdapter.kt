package com.example.footballdetails.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballdetails.databinding.FragmentCountriesListBinding
import com.example.footballdetails.dataclass.CountriesData

import com.example.footballdetails.ui.listeners.CountryClickListener


class CountryPagingAdapter( private val listener: CountryClickListener) :
    PagingDataAdapter<CountriesData, CountryPagingAdapter.CountryViewHolder>(COMPARATOR) {



    class CountryViewHolder(private val binding: FragmentCountriesListBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(model: CountriesData,listener: CountryClickListener) =
            with(binding){

            content.text = model.name
            officialName.text=model.official_name
            Glide.with(itemView).load(model.image_path)
                .into(imageCountry)


            leagues.setOnClickListener{
              listener.onItemClicked(model,it)
            }
                teams.setOnClickListener{
                    listener.onItemClicked(model,it)
                }
                players.setOnClickListener{
                    listener.onItemClicked(model,it)
                }
           /* itemView.setOnClickListener {
                listener.onItemClicked(model)
            }*/
        }
    }


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

       /* val item = getItem(position)
        if (item != null) {
            holder.quote.text = item.name
        }*/
        getItem(position)?.let { holder.bind(it, listener) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
       /* val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_countries_list, parent, false)
        return CountryViewHolder(view)*/

        val binding = FragmentCountriesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CountryViewHolder(binding)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CountriesData>() {
            override fun areItemsTheSame(oldItem: CountriesData, newItem: CountriesData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CountriesData, newItem: CountriesData): Boolean {
                return oldItem == newItem
            }
        }
    }



}





















