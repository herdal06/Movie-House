package com.herdal.retrofitpaging.data.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.herdal.retrofitpaging.data.remote.ApiService
import com.herdal.retrofitpaging.data.remote.model.movie_list.Movie

class TopRatedMoviesPagingSource(private val apiService: ApiService) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getTopRatedMovies(nextPageNumber)

            LoadResult.Page(
                data = response.movies,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.total_pages) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}