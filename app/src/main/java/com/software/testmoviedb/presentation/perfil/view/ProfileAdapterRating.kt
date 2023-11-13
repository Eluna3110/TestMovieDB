package com.software.testmoviedb.presentation.perfil.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.software.testmoviedb.R
import com.software.testmoviedb.databinding.ItemRatingMovieBinding
import com.software.testmoviedb.domain.model.ProfileMovieRating

class ProfileAdapterRating(val data : List<ProfileMovieRating>) : RecyclerView.Adapter<ProfileAdapterRating.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemRatingMovieBinding>(inflater, R.layout.item_rating_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(val binding: ItemRatingMovieBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: ProfileMovieRating) {
            binding.movie = item

            binding.ratingBar.rating = item.rating
            binding.imageView.setImageResource(item.iconImage)
            binding.executePendingBindings()
        }
    }
}