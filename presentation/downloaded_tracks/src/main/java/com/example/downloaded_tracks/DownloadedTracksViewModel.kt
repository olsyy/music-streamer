package com.example.downloaded_tracks

import androidx.lifecycle.viewModelScope
import com.example.core.state.Loading
import com.example.domain.usecases.GetTracksLocallyUseCase
import com.example.domain.usecases.SearchTracksLocallyUseCase
import com.example.shared_ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadedTracksViewModel @Inject constructor(
    private val getTracksUseCase: GetTracksLocallyUseCase,
    private val searchTracksUseCase: SearchTracksLocallyUseCase,
) : BaseViewModel() {

    init {
        _tracks.value = Loading
    }

    override fun loadTracks() = viewModelScope.launch {
        _tracks.value = Loading
        _tracks.value = getTracksUseCase()
    }

    override fun searchTracks(query: String) = viewModelScope.launch {
        _tracks.value = Loading
        _tracks.value = searchTracksUseCase(query)
    }
}