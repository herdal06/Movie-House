package com.herdal.retrofitpaging.data.repository

import com.herdal.retrofitpaging.data.remote.ApiService
import com.herdal.retrofitpaging.data.remote.model.movie_details.MovieDetails
import com.herdal.retrofitpaging.domain.repository.MovieRepository
import com.herdal.retrofitpaging.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {
    override fun getMovieDetails(id: Int): Flow<Resource<MovieDetails>> = flow {
        val movie = apiService.getUserById(id)
        emit(Resource.Success(movie))
    }.flowOn(Dispatchers.IO)
}