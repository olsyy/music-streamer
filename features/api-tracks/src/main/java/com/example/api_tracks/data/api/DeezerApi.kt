package com.example.api_tracks.data.api

import com.example.api_tracks.data.entities.TrackResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface DeezerApi {

    @GET("chart")
    suspend fun fetchTracks(): Response<TrackResponseDto>

    @GET("search")
    suspend fun searchTracks(query: String): Response<TrackResponseDto>
}