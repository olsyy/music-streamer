package com.example.domain.entities

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

typealias TracksList = List<Track>

@Parcelize
data class Track(
    val id: Long,
    val title: String,
    val artist: String,
    val cover: Uri?,
    val audioSourceUrl: Uri,
) : Parcelable
