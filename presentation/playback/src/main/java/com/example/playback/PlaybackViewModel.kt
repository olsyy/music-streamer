package com.example.playback

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.state.Loading
import com.example.core.state.PlaybackSource
import com.example.core.state.Success
import com.example.core.state.ViewState
import com.example.domain.entities.Track
import com.example.domain.usecases.GetTrackToPlayLocallyUseCase
import com.example.domain.usecases.GetTrackToPlayRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaybackViewModel @Inject constructor(
    private val getTrackToPlayRemote: GetTrackToPlayRemoteUseCase,
    private val getTrackToPlayLocally: GetTrackToPlayLocallyUseCase,
) : ViewModel() {

    var tracks: MutableList<Track> = mutableListOf()
    var trackId: Long = 0L
    var source: PlaybackSource = PlaybackSource.API

    private val _track = MutableLiveData<ViewState<Track>>()
    val track: LiveData<ViewState<Track>> = _track

    init {
        _track.value = Loading
    }

    fun loadTrack(trackId: Long) = viewModelScope.launch {
        _track.value = when (source) {
            PlaybackSource.API -> {
                Log.d("PlaybackFragmentS", "Get track to play from Api")
                getTrackToPlayRemote(trackId)
            }

            PlaybackSource.LOCAL -> {
                Log.d("PlaybackFragmentS", "Get track to play from Local")
                getTrackToPlayLocally(trackId)
            }
        }
    }
}