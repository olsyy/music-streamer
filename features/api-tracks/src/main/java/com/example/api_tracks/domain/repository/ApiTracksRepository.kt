package com.example.api_tracks.domain.repository

import com.example.api_tracks.domain.entities.Track
import com.example.core.api_response.ApiResponse

interface ApiTracksRepository {

    suspend fun getTracks(): ApiResponse<List<Track>>

    suspend fun searchTracks(query: String): ApiResponse<List<Track>>
}