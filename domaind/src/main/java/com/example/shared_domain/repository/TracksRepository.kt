package com.example.shared_domain.repository

import com.example.core.api_response.ApiResponse
import com.example.shared_domain.entities.TracksList

interface TracksRepository {

    suspend fun getTracks(): ApiResponse<TracksList>

    suspend fun searchTracks(query: String): ApiResponse<TracksList>
}