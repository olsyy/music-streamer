package com.example.api_tracks.domain.repository

import com.example.api_tracks.domain.entities.TracksList
import com.example.core.api_response.ApiResponse

interface ApiTracksRepository {

    suspend fun getTracks(): ApiResponse<TracksList>

    suspend fun searchTracks(query: String): ApiResponse<TracksList>
}