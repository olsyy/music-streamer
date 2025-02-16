package com.example.data.repository

import com.example.core.state.ViewState
import com.example.core.state.handleApi
import com.example.data.api.PlaybackApiService
import com.example.data.mapper.toTrack
import com.example.domain.entities.Track
import com.example.domain.repository.PlaybackRepository
import javax.inject.Inject

class ApiPlaybackRepositoryImpl @Inject constructor(
    private val api: PlaybackApiService,
) : PlaybackRepository {

    override suspend fun getTrackToPlay(trackId: Long): ViewState<Track> {
        return handleApi(
            execute = {
                val data = api.getPlaybackUrl(trackId)
                data
            },
            transform = { track -> track.toTrack() }
        )
    }
}