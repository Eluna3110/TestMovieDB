package com.software.testmoviedb.presentation.movie.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.software.testmoviedb.BuildConfig
import com.software.testmoviedb.R
import com.software.testmoviedb.databinding.ItemMovieBinding
import com.software.testmoviedb.domain.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(val context: Context) : ListAdapter<Movie.Results,MovieAdapter.ViewHolder>(ItemCallbackDiff())
{
    class ItemCallbackDiff : DiffUtil.ItemCallback<Movie.Results>(){
        override fun areItemsTheSame(oldItem: Movie.Results, newItem: Movie.Results): Boolean = oldItem == newItem
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Movie.Results, newItem: Movie.Results): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)
    {
        private val baseUrl = BuildConfig.BASE_URL_IMAGE
        fun bind(item: Movie.Results) {
            binding.movie = item

            Picasso.with(context)
                .load(baseUrl + item.poster_path)
                .into(binding.imageView)

            binding.executePendingBindings()
        }
    }
}