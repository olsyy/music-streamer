package com.example.data.di

import com.example.core.di.LocalRepo
import com.example.core.di.RemoteRepo
import com.example.core.response.DataResponse
import com.example.data.repository.ApiTracksRepositoryImpl
import com.example.data.repository.DownloadedTracksRepositoryIml
import com.example.domain.entities.TracksList
import com.example.domain.repository.LocalDataRepository
import com.example.domain.repository.RemoteDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideApiTracksRepository(impl: ApiTracksRepositoryImpl): RemoteDataRepository

    @Binds
    fun provideDownloadedTracksRepository(impl: DownloadedTracksRepositoryIml): LocalDataRepository
}