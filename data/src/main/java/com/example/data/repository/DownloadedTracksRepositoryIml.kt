package com.example.data.repository

import android.content.Context
import android.provider.MediaStore
import android.util.Log
import com.example.domain.entities.Track
import com.example.domain.entities.TracksList
import com.example.domain.repository.LocalDataRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DownloadedTracksRepositoryIml @Inject constructor(
    @ApplicationContext private val context: Context,
) : LocalDataRepository {

    private val tracks = mutableListOf<Track>()

    override suspend fun getTracks(): TracksList {
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns._ID,
            MediaStore.Audio.AudioColumns.DISPLAY_NAME,
            MediaStore.Audio.AudioColumns.ARTIST,
            MediaStore.Audio.AudioColumns.ALBUM,
        )

        val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
        val sortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER
        val musicCursor = context.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection, selection, null, sortOrder
        )

       withContext(Dispatchers.IO) {
            musicCursor?.use { cursor ->

                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns._ID)
                val nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.DISPLAY_NAME)
                val artistColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST)
                val albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val artist = cursor.getString(artistColumn)
                    val album = cursor.getString(albumColumn)

                    val track = Track(id, name, artist, album)
                    tracks.add(track)
                }
            }
        }
        Log.d("TAGsTracks", "getTracks: ${tracks}")

        return tracks
    }

    override suspend fun searchTracks(query: String): TracksList {
        TODO("Not yet implemented")
    }
}