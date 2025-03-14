package com.example.service

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.domain.entities.Track
import com.example.domain.playback.PlayerController

import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PlayerControllerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val playbackService: PlaybackService,
    private val player: ExoPlayer,
) : PlayerController {

    init {
        if (player.isPlaying) {
            player.stop()
            player.clearMediaItems()
        }
        startPlaybackService()
    }

    fun startPlaybackService() {
        val intent = Intent(context, PlaybackService::class.java)
        ContextCompat.startForegroundService(context, intent)
    }

    override fun playTrack(
        trackId: Long,
        tracks: List<Track>,
    ) {
        val mediaItems = tracks.map { track ->
            MediaItem.fromUri(track.audioSourceUrl)
        }
        player.setMediaItems(mediaItems)

        val currentIndex = tracks.indexOfFirst { it.id == trackId }

        player.seekTo(currentIndex, 0)
        player.prepare()
        player.play()
    }

    override fun setPlayerListener(
        track: Track,
        onTrackChanged: (Track) -> Unit
    ) {
        player.addListener(object : Player.Listener {


            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                onTrackChanged(track)
            }
        })
    }
}