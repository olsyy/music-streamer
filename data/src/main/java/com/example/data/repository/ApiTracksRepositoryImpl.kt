package com.example.data.repository

import com.example.core.response.DataResponse
import com.example.core.response.handleApi
import com.example.data.api.DeezerApi
import com.example.data.mapper.toListTracks
import com.example.domain.entities.TracksList
import com.example.domain.repository.RemoteDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiTracksRepositoryImpl @Inject constructor(
    private val api: DeezerApi,
) : RemoteDataRepository {

    override suspend fun getTracks(): DataResponse<TracksList> {
        return handleApi(
            execute = {
                val data = api.fetchTracks()
                data
            },
            transform = { tracks -> tracks.toListTracks() }
        )
    }

    override suspend fun searchTracks(query: String): DataResponse<TracksList> {
        return handleApi(
            execute = {
                val data = api.searchTracks(query)
                data
            },
            transform = { tracks -> tracks.toListTracks() }
        )
    }
}