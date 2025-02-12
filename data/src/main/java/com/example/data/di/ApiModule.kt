package com.example.data.di


import com.example.data.api.DeezerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideApiTracksApi(retrofit: Retrofit): DeezerApi {
        return retrofit.create(DeezerApi::class.java)
    }
}