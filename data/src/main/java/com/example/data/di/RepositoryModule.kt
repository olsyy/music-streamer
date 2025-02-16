package com.example.data.di

import android.content.Context
import com.example.core.di.LocalRepo
import com.example.core.di.RemoteRepo
import com.example.data.api.MusicApiService
import com.example.data.api.PlaybackApiService
import com.example.data.repository.ApiPlaybackRepositoryImpl
import com.example.data.repository.ApiTracksRepositoryImpl
import com.example.data.repository.DownloadedTracksRepositoryIml
import com.example.data.repository.DownloadedPlaybackRepositoryImpl
import com.example.domain.repository.PlaybackRepository
import com.example.domain.repository.TracksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @RemoteRepo
    @Provides
    @Singleton
    fun provideApiTracksRepository(api: MusicApiService): TracksRepository =
        ApiTracksRepositoryImpl(api)

    @LocalRepo
    @Provides
    @Singleton
    fun provideDownloadedTracksRepository(@ApplicationContext context: Context): TracksRepository {
        return DownloadedTracksRepositoryIml(context)
    }

    @RemoteRepo
    @Provides
    @Singleton
    fun provideApiPlaybackRepository(api: PlaybackApiService): PlaybackRepository =
        ApiPlaybackRepositoryImpl(api)

    @LocalRepo
    @Provides
    @Singleton
    fun provideLocalPlaybackRepository(@ApplicationContext context: Context): PlaybackRepository =
        DownloadedPlaybackRepositoryImpl(context)
}