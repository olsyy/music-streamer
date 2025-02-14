package com.example.domain.entities

import android.net.Uri

typealias TracksList = List<Track>

data class Track(
    val id: Long,
    val title: String,
    val artist: String,
    val cover: Uri?,
)
