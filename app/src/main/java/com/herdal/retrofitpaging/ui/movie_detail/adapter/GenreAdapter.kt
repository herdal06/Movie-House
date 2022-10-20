package com.herdal.retrofitpaging.ui.movie_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.herdal.retrofitpaging.data.remote.model.movie_details.Genre
import com.herdal.retrofitpaging.databinding.ItemGenreBinding

class GenreAdapter : ListAdapter<Genre, GenreViewHolder>(DiffCallBack) {

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder(
            ItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val currentGenre = getItem(position)
        currentGenre?.let {
            holder.bind(currentGenre)
        }
    }
}