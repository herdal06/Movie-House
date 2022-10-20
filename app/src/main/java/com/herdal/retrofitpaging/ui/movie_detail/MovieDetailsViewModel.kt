package com.herdal.retrofitpaging.ui.movie_detail

import androidx.lifecycle.ViewModel
import com.herdal.retrofitpaging.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
}