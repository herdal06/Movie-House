package com.herdal.retrofitpaging.ui.movies.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.retrofitpaging.data.remote.model.movie_list.Movie
import com.herdal.retrofitpaging.data.remote.util.ApiConstants
import com.herdal.retrofitpaging.databinding.ItemMovieBinding
import com.herdal.retrofitpaging.util.extensions.loadImage

class MoviesViewHolder(
    private val binding: ItemMovieBinding,
    private val onClickMovie: ((movieId: Int) -> Unit)?
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) = binding.apply {
        ivMovie.loadImage(ApiConstants.getPosterPath(movie.poster_path))
        tvOriginalTitle.text = movie.title
        tvVoteAverage.text = movie.vote_average.toString()

        itemView.setOnClickListener {
            onClickMovie?.invoke(movie.id)
        }
    }
}