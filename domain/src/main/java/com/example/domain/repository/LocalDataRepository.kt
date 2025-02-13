package com.example.domain.repository

import com.example.core.response.DataResponse
import com.example.domain.entities.TracksList

interface LocalDataRepository {

    suspend fun getTracks(): TracksList
    suspend fun searchTracks(query: String): TracksList
}