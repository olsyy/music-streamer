package com.example.data.api

import com.example.data.entities.TrackDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaybackApiService {

    @GET("track/{trackId}")
    suspend fun getPlaybackUrl(@Path("trackId") trackId: Long): Response<TrackDto>
}