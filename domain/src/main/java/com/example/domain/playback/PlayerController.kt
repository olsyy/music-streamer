package com.example.domain.playback

import com.example.domain.entities.Track

interface PlayerController {

    fun playTrack(trackId: Long, tracks: List<Track>, )
    fun setPlayerListener(track: Track, onTrackChanged: (Track) -> Unit)
}