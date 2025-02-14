package com.example.data.music_store_helper

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.example.core.state.Success
import com.example.core.state.ViewState
import com.example.domain.entities.Track
import com.example.domain.entities.TracksList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MediaStoreHelper {

    suspend fun getTracks(
        context: Context,
        selection: String? = null,
        selectionArgs: Array<String>? = null,
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
            MediaStore.Audio.AudioColumns.ALBUM_ID
        )

        val sortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER

        val musicCursor = context.contentResolver.query(
            collection,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )

        withContext(Dispatchers.IO) {
            musicCursor?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns._ID)
                val nameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE)
                val artistColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ARTIST)
                val albumIdColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM_ID)

                while (cursor.moveToNext()) {

                    val id = cursor.getLong(idColumn)
                    val name = cursor.getString(nameColumn)
                    val artist = cursor.getString(artistColumn)
                    val cover = cursor.getLong(albumIdColumn).let { albumId ->
                        val sArt = Uri.parse("content://media/external/audio/albumart")
                        ContentUris.withAppendedId(sArt, albumId)
                    }

                    val track = Track(id, name, artist, cover)
                    tracks.add(track)
                }
            }
        }
        return Success(tracks)
    }
}