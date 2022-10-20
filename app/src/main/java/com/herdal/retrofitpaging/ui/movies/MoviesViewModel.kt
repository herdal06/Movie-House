package com.herdal.retrofitpaging.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.herdal.retrofitpaging.data.remote.ApiService
import com.herdal.retrofitpaging.data.remote.pagingsource.NowPlayingMoviesPagingSource
import com.herdal.retrofitpaging.data.remote.pagingsource.PopularMoviesPagingSource
import com.herdal.retrofitpaging.data.remote.pagingsource.TopRatedMoviesPagingSource
import com.herdal.retrofitpaging.data.remote.pagingsource.UpcomingMoviesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    val popularMovies = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
        PopularMoviesPagingSource(apiService)
    }).flow.cachedIn(viewModelScope)

    val topRatedMovies = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
        TopRatedMoviesPagingSource(apiService)
    }).flow.cachedIn(viewModelScope)

    val upcomingMovies = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
        UpcomingMoviesPagingSource(apiService)
    }).flow.cachedIn(viewModelScope)

    val nowPlayingMovies = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
        NowPlayingMoviesPagingSource(apiService)
    }).flow.cachedIn(viewModelScope)
}