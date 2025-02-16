package com.example.data.repository

import com.example.core.state.ViewState
import com.example.core.state.handleApi
import com.example.data.api.MusicApiService
import com.example.data.mapper.toListTracks
import com.example.domain.entities.TracksList
import com.example.domain.repository.TracksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiTracksRepositoryImpl @Inject constructor(
    private val api: MusicApiService,
) : TracksRepository {

    override suspend fun getTracks(): ViewState<TracksList> {
        return handleApi(
            execute = {
                val data = api.fetchTracks()
                data
            },
            transform = { tracks -> tracks.toListTracks() }
        )
    }

    override suspend fun searchTracks(query: String): ViewState<TracksList> {
        return handleApi(
            execute = {
                val data = api.searchTracks(query)
                data
            },
            transform = { tracks -> tracks.toListTracks() }
        )
    }
}