package com.herdal.retrofitpaging.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.herdal.retrofitpaging.data.remote.model.Movie
import com.herdal.retrofitpaging.databinding.ItemMovieBinding

class MoviesViewHolder(private val binding: ItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) = binding.apply {
        ivMovie.load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
        tvOriginalTitle.text = movie.title
        tvVoteAverage.text = movie.vote_average.toString()
    }
}