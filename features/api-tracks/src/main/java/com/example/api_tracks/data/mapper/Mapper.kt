package com.example.api_tracks.data.mapper

import com.example.api_tracks.data.entities.ArtistDto
import com.example.api_tracks.data.entities.TrackDataDto
import com.example.api_tracks.data.entities.TrackDto
import com.example.api_tracks.data.entities.TrackResponseDto
import com.example.api_tracks.domain.entities.Track
import com.example.core.api_response.*

fun TrackDto.toTrack() = Track(
    id = id,
    title = title,
    artist = artist.name,
//    cover = cover ?: "",
)

fun Track.toTrackDto() = TrackDto(
    id = id,
    title = title,
    artist = ArtistDto(artist),
//    cover = cover,
)

fun TrackDataDto.toListTracks() = data.map { it.toTrack() }

fun TrackResponseDto.toListTracks() : List<Track> = tracks.toListTracks()

fun <T : Any, R : Any> ApiResponse<T>.map(transform: (T) -> R): ApiResponse<R> {
    return when (this) {
        is Success -> Success(transform(this.data))
        is Error -> Error(this.code, this.message)
        is Exception -> Exception(this.e)
        Empty, Loading -> this
    } as ApiResponse<R>
}