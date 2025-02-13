package com.example.data.repository

import com.example.core.response.Response
import com.example.domain.entities.TracksList
import com.example.domain.repository.TracksRepository

class DownloadedTracksRepositoryIml : TracksRepository {

    override suspend fun getTracks(): Response<TracksList> {
        TODO("Not yet implemented")
    }

    override suspend fun searchTracks(query: String): Response<TracksList> {
        TODO("Not yet implemented")
    }
}