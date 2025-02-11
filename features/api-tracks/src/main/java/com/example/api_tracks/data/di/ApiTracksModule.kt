package com.example.api_tracks.data.di

import com.example.api_tracks.data.api.DeezerApi
import com.example.api_tracks.data.repository.ApiTracksRepositoryImpl
import com.example.api_tracks.domain.repository.ApiTracksRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiTracksModule {

    @Binds
    abstract fun provideApiTracksRepository(impl: ApiTracksRepositoryImpl): ApiTracksRepository

    companion object {
        @Provides
        fun provideApiTracksApi(retrofit: Retrofit): DeezerApi {
            return retrofit.create(DeezerApi::class.java)
        }
    }
}