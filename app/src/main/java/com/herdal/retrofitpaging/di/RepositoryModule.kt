package com.herdal.retrofitpaging.di

import com.herdal.retrofitpaging.data.remote.ApiService
import com.herdal.retrofitpaging.data.repository.MovieRepositoryImpl
import com.herdal.retrofitpaging.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providePostRepository(apiService: ApiService): MovieRepository =
        MovieRepositoryImpl(apiService)
}