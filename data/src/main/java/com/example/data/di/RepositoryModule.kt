package com.example.data.di

import android.content.Context
import com.example.core.di.LocalRepo
import com.example.core.di.RemoteRepo
import com.example.data.repository.ApiTracksRepositoryImpl
import com.example.data.repository.DownloadedTracksRepositoryIml
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
    fun provideApiTracksRepository(impl: ApiTracksRepositoryImpl): TracksRepository = impl

    @LocalRepo
    @Provides
    @Singleton
    fun provideDownloadedTracksRepository(@ApplicationContext context: Context): TracksRepository {
        return DownloadedTracksRepositoryIml(context)
    }
}