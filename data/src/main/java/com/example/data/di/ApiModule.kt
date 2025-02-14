package com.example.data.di


import com.example.data.api.DeezerApi
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
    fun provideApiTracksApi(retrofit: Retrofit): DeezerApi {
        return retrofit.create(DeezerApi::class.java)
    }
}