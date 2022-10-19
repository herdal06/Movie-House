package com.herdal.retrofitpaging.data.remote


import com.herdal.retrofitpaging.BuildConfig
import com.herdal.retrofitpaging.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieResponse
}