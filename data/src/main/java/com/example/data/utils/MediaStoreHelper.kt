package com.example.data.utils

import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.example.core.state.Success
import com.example.core.state.ViewState
import com.example.domain.entities.Track
import com.example.domain.entities.TracksList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MediaStoreHelper {

    suspend fun getTracks(context: Context,
                          selection: String? = null,
                          selectionArgs: Array<String>? = null,
                          query: String? = null
    ): ViewState<TracksList> {
        val tracks = mutableListOf<Track>()

        val collection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Audio.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
                )
            } else {
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            }

        val projection = arrayOf(
            MediaStore.Audio.AudioColumns._ID,
            MediaStore.Audio.AudioColumns.TITLE,
            MediaStore.Audio.AudioColumns.ARTIST,
            MediaStore.Audio.AudioColumns.ALBUM,
        )

        val sortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER

        val musicCursor = context.contentResolver.query(
            collection,
            projection,
            selection,
            null,
            sortOrder
        )

        withContext(Dispatchers.IO) {
            musicCursor?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns._ID)
                val nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE)
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
        Log.d("TAGsTracks", "getTracks: $tracks")

        return Success(tracks)
    }
}