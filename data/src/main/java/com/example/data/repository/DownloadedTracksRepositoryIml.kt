package com.example.data.repository

import android.content.Context
import android.provider.MediaStore
import com.example.core.state.ViewState
import com.example.data.music_store_helper.MediaStoreHelper
import com.example.domain.entities.TracksList
import com.example.domain.repository.TracksRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DownloadedTracksRepositoryIml @Inject constructor(
    @ApplicationContext private val context: Context,
) : TracksRepository {

    override suspend fun getTracks(): ViewState<TracksList> {
        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        return MediaStoreHelper.getTracks(context, selection)
    }

    override suspend fun searchTracks(query: String): ViewState<TracksList> {
        val selection =
            "${MediaStore.Audio.Media.TITLE} LIKE ? OR ${MediaStore.Audio.Media.ARTIST} LIKE ?"
        val selectionArgs = arrayOf("%$query%", "%$query%")

        return MediaStoreHelper.getTracks(
            context,
            selection = selection,
            selectionArgs = selectionArgs
        )
    }
}