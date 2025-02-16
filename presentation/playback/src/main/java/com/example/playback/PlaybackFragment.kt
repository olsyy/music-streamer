package com.example.playback

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.core.state.Empty
import com.example.core.state.Error
import com.example.core.state.Exception
import com.example.core.state.Loading
import com.example.core.state.PlaybackSource
import com.example.core.state.Success
import com.example.domain.entities.Track
import com.example.playback.databinding.FragmentPlaybackBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaybackFragment : Fragment() {

    private var _binding: FragmentPlaybackBinding? = null
    private val binding: FragmentPlaybackBinding get() = _binding!!

    private val viewModel: PlaybackViewModel by viewModels()

    private lateinit var player: ExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaybackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setupPlayer()
        setPlayerListener()
        setupObservers()
        updateTrackInfo(viewModel.tracks.find { it.id == viewModel.trackId }!!)
    }

    private fun initViewModel() {
        val trackId = arguments?.getLong("trackId", -1L) ?: -1L
        val playbackSource =
            arguments?.getString("playbackSource")?.let { PlaybackSource.valueOf(it) }
                ?: PlaybackSource.API
        val allTracks = arguments?.getParcelableArrayList<Track>("allTracks") ?: emptyList()
        viewModel.trackId = trackId
        viewModel.tracks = allTracks.toMutableList()
        viewModel.source = playbackSource
        viewModel.loadTrack(trackId)
    }

    @OptIn(UnstableApi::class)
    private fun setupObservers() {
        viewModel.track.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Success -> {
                    binding.progressBar.visibility = View.GONE
                }

                is Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Error -> {
                    Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                }

                is Exception -> {
                    Toast.makeText(context, response.e.message, Toast.LENGTH_SHORT).show()
                }

                is Empty -> {}
            }
        }
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = player

        val mediaItems = viewModel.tracks.map { track ->
            MediaItem.fromUri(track.audioSourceUrl)
        }

        player.setMediaItems(mediaItems)

        player.seekTo(
            viewModel.tracks.indexOf(viewModel.tracks.find { it.id == viewModel.trackId }),
            0
        )

        player.prepare()
        player.play()
    }

    private fun setPlayerListener() {
        player.addListener(object : Player.Listener {
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                val newIndex = player.currentMediaItemIndex
                val currentTrack = viewModel.tracks[newIndex]
                updateTrackInfo(currentTrack)
            }
        })
    }

    private fun updateTrackInfo(track: Track) {
        binding.textViewTitle.text = track.title
        binding.textViewArtist.text = track.artist
        loadTrackCover(track.cover)
    }

    private fun loadTrackCover(coverUrl: Uri?) {
        Glide.with(requireContext())
            .load(coverUrl)
            .into(object : CustomTarget<Drawable>() {
                @OptIn(UnstableApi::class)
                override fun onResourceReady(
                    resource: Drawable,
                    transition: com.bumptech.glide.request.transition.Transition<in Drawable>?
                ) {
                    binding.playerView.defaultArtwork = resource
                }

                @OptIn(UnstableApi::class)
                override fun onLoadCleared(placeholder: Drawable?) {
                    binding.playerView.defaultArtwork = placeholder ?: ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.base_image_track_cover
                    )
                }
            })
    }

    override fun onDestroy() {
        player.release()
        super.onDestroy()
    }
}