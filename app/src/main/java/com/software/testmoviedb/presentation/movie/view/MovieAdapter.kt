package com.software.testmoviedb.presentation.movie.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.software.testmoviedb.R
import com.software.testmoviedb.databinding.ItemMovieBinding
import com.software.testmoviedb.domain.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(var data : List<Movie.Results>,val context: Context) :  RecyclerView.Adapter<MovieAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMovieBinding>(inflater, R.layout.item_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class ViewHolder(val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root)
    {
        private val baseUrl = "https://image.tmdb.org/t/p/w500"
        fun bind(item: Movie.Results) {
            binding.movie = item

            Picasso.with(context)
                .load(baseUrl + item.poster_path)
                .into(binding.imageView)

            binding.executePendingBindings()
        }
    }

    fun updateDataSet(newData: List<Movie.Results>) {
        this.data = newData
        notifyDataSetChanged()
    }
}