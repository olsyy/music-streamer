package com.example.data.repository

import com.example.core.response.Response
import com.example.core.response.handleApi
import com.example.data.api.DeezerApi
import com.example.data.mapper.toListTracks
import com.example.domain.entities.TracksList
import com.example.domain.repository.TracksRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiTracksRepositoryImpl @Inject constructor(
    private val api: DeezerApi,
) : TracksRepository {

    override suspend fun getTracks(): Response<TracksList> {
        return handleApi(
            execute = {
                val data = api.fetchTracks()
//                Log.d("TAGsTracks", "getTracks: $data")
                data
            },
            transform = { tracks -> tracks.toListTracks() }
        )
    }

    override suspend fun searchTracks(query: String): Response<TracksList> {
        return handleApi(
            execute = {
//                Log.d("TAGsTracks", "QueryApi: $query")
                val data = api.searchTracks(query)
//                Log.d("TAGsTracks", "getTracks: $data")
                data
            },
            transform = { tracks -> tracks.toListTracks() }
        )
    }
}