package com.herdal.retrofitpaging.ui.movie_detail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.herdal.retrofitpaging.data.remote.model.movie_details.Genre
import com.herdal.retrofitpaging.databinding.ItemGenreBinding

class GenreViewHolder(private val binding: ItemGenreBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(genre: Genre) = binding.apply {
        tvGenre.text = genre.name
    }
}