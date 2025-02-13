package com.example.data.di

import com.example.core.di.LocalRepo
import com.example.core.di.RemoteRepo
import com.example.data.repository.ApiTracksRepositoryImpl
import com.example.data.repository.DownloadedTracksRepositoryIml
import com.example.domain.repository.TracksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @RemoteRepo
    @Binds
    fun provideApiTracksRepository(impl: ApiTracksRepositoryImpl): TracksRepository

    @LocalRepo
    @Binds
    fun provideDownloadedTracksRepository(impl: DownloadedTracksRepositoryIml): TracksRepository
}