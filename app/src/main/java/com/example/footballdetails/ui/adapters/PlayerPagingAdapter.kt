package com.example.footballdetails.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballdetails.R
import com.example.footballdetails.databinding.FragmentPlayerListBinding
import com.example.footballdetails.dataclass.PlayersData
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter


class PlayerPagingAdapter() :
    PagingDataAdapter<PlayersData, PlayerPagingAdapter.PlayerViewHolder>(COMPARATOR) {



    class PlayerViewHolder(private val binding: FragmentPlayerListBinding) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(model: PlayersData) =
            with(binding){
                name.text = model.name
                @RequiresApi(Build.VERSION_CODES.O)
                age.text= "Age : "+getAge(model.date_of_birth)

                height.text= "Height : "+model.height
                weight.text="Weight : "+model.weight
                Glide.with(itemView).load(model.image_path)
                    .into(imagePlayer)
/*
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
                }*/

        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun getAge(dateOfBirth: String): Any? {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") //1984-12-12
            val localDate = LocalDate.parse(dateOfBirth, formatter)
            val year=localDate.year
            val month=localDate.month
            val dayOfMonth=localDate.dayOfMonth
            return Period.between(
                LocalDate.of(year, month, dayOfMonth),
                LocalDate.now()
            ).years
        }
    }


    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {

       /* val item = getItem(position)
        if (item != null) {
            holder.quote.text = item.name
        }*/
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
       /* val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_countries_list, parent, false)
        return CountryViewHolder(view)*/

        val binding = FragmentPlayerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PlayerViewHolder(binding)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PlayersData>() {
            override fun areItemsTheSame(oldItem: PlayersData, newItem: PlayersData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PlayersData, newItem: PlayersData): Boolean {
                return oldItem == newItem
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAge(dob:String): Int {

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM.dd") //1984-12-12
        val localDate = LocalDate.parse(dob, formatter)
        val year=localDate.year
        val month=localDate.month
        val dayOfMonth=localDate.dayOfMonth
        return Period.between(
            LocalDate.of(year, month, dayOfMonth),
            LocalDate.now()
        ).years


    }


}





















