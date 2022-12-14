package com.herdal.retrofitpaging.data.remote

import com.herdal.retrofitpaging.BuildConfig
import com.herdal.retrofitpaging.data.remote.model.movie_details.MovieDetails
import com.herdal.retrofitpaging.data.remote.model.movie_list.MovieResponse
import com.herdal.retrofitpaging.data.remote.util.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ApiConstants.Endpoints.POPULAR)
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET(ApiConstants.Endpoints.TOP_RATED)
    suspend fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET(ApiConstants.Endpoints.UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET(ApiConstants.Endpoints.NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET(ApiConstants.Endpoints.MOVIE_DETAILS)
    suspend fun getUserById(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): MovieDetails
}