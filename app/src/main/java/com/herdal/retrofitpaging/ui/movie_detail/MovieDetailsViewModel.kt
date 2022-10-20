package com.herdal.retrofitpaging.ui.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.retrofitpaging.data.remote.model.movie_details.MovieDetails
import com.herdal.retrofitpaging.domain.repository.MovieRepository
import com.herdal.retrofitpaging.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movie = MutableStateFlow<Resource<MovieDetails>>(Resource.Loading)
    val movie: StateFlow<Resource<MovieDetails>> = _movie

    fun getMovieById(id: Int) = viewModelScope.launch {
        movieRepository.getMovieDetails(id).catch {
        }.collect {
            _movie.value = it
        }
    }
}