package com.herdal.retrofitpaging.domain.repository

import com.herdal.retrofitpaging.data.remote.model.movie_details.MovieDetails
import com.herdal.retrofitpaging.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieDetails(id: Int): Flow<Resource<MovieDetails>>
}