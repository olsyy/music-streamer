package com.example.data.repository

import android.content.Context
import com.example.core.state.ViewState
import com.example.data.music_store_helper.MediaStoreHelper
import com.example.domain.entities.Track
import com.example.domain.repository.PlaybackRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DownloadedPlaybackRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : PlaybackRepository {

    override suspend fun getTrackToPlay(trackId: Long): ViewState<Track> {
        return MediaStoreHelper.getTrackToPlay(context, trackId)
    }
}