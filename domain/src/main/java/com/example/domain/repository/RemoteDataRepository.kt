package com.example.domain.repository

import com.example.core.response.DataResponse
import com.example.domain.entities.TracksList

interface RemoteDataRepository {

    suspend fun getTracks(): DataResponse<TracksList>
    suspend fun searchTracks(query: String): DataResponse<TracksList>
}