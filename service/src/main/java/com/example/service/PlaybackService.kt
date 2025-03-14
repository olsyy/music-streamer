package com.example.service

import android.content.Intent
import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaybackService @Inject constructor(): MediaSessionService() {

    private var mediaSession: MediaSession? = null

    @Inject
    lateinit var exoPlayer: ExoPlayer

    override fun onCreate() {
        Log.d("PlaybackFragmentS", "PlaybackService onCreate()")
        super.onCreate()
        initMediaSession()
    }

    @OptIn(UnstableApi::class)
    private fun initMediaSession() {
        mediaSession = MediaSession.Builder(this, exoPlayer)
            .setCallback(MediaSessionCallback())
            .build()

//        setMediaNotificationProvider(
//            DefaultMediaNotificationProvider.Builder(applicationContext).build().apply {
//
//            }
//        )
    }

    private inner class MediaSessionCallback : MediaSession.Callback {
        override fun onAddMediaItems(
            mediaSession: MediaSession,
            controller: MediaSession.ControllerInfo,
            mediaItems: MutableList<MediaItem>
        ): ListenableFuture<MutableList<MediaItem>> {
            val updatedMediaItems = mediaItems.map {
                it.buildUpon().setUri(it.mediaId).build()
            }.toMutableList()
            return Futures.immediateFuture(updatedMediaItems)
        }
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        mediaSession?.player?.let { player ->
            if (player.playWhenReady) {
                player.pause()
            }
        }
        stopSelf()
    }
}