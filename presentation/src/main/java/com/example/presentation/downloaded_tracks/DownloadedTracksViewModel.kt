package com.example.presentation.downloaded_tracks

import androidx.lifecycle.viewModelScope
import com.example.core.response.DataResponse
import com.example.domain.entities.TracksList
import com.example.domain.usecases.GetTracksLocallyUseCase
import com.example.domain.usecases.SearchTracksLocallyUseCase
import com.example.presentation.base_ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadedTracksViewModel @Inject constructor(
    private val getTracksUseCase: GetTracksLocallyUseCase,
    private val searchTracksUseCase: SearchTracksLocallyUseCase,
) : BaseViewModel<TracksList>() {

    override fun loadTracks() = viewModelScope.launch {
        _tracks.value = getTracksUseCase()
    }

    override fun searchTracks(query: String) = viewModelScope.launch {

    }
}