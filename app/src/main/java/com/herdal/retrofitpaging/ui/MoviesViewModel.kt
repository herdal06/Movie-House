package com.herdal.retrofitpaging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.herdal.retrofitpaging.data.remote.ApiService
import com.herdal.retrofitpaging.data.remote.pagingsource.MoviePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    val popularMovies = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
        MoviePagingSource(apiService)
    }).flow.cachedIn(viewModelScope)
}