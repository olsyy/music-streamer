package com.example.data.di


import com.example.data.api.MusicApiService
import com.example.data.api.PlaybackApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideMusicApi(retrofit: Retrofit): MusicApiService {
        return retrofit.create(MusicApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePlaybackApi(retrofit: Retrofit): PlaybackApiService {
        return retrofit.create(PlaybackApiService::class.java)
    }
}