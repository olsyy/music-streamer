package com.example.domain.repository

import com.example.core.api_response.ApiResponse
import com.example.domain.entities.TracksList

interface TracksRepository {

    suspend fun getTracks(): ApiResponse<TracksList>

    suspend fun searchTracks(query: String): ApiResponse<TracksList>
}