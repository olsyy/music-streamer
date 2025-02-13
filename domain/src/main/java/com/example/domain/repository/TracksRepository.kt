package com.example.domain.repository

import com.example.core.response.Response
import com.example.domain.entities.TracksList

interface TracksRepository {

    suspend fun getTracks(): Response<TracksList>

    suspend fun searchTracks(query: String): Response<TracksList>
}