package com.example.api_tracks

import androidx.lifecycle.viewModelScope
import com.example.core.state.Loading
import com.example.domain.usecases.GetTracksRemoteUseCase
import com.example.domain.usecases.SearchTracksRemoteUseCase
import com.example.shared_ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiTracksViewModel @Inject constructor(
    private val getTracksUseCase: GetTracksRemoteUseCase,
    private val searchTracksUseCase: SearchTracksRemoteUseCase,
) : BaseViewModel() {

    init {
        _tracks.value = Loading
    }

    override fun loadTracks() = viewModelScope.launch {
        _tracks.value = getTracksUseCase()
    }

    override fun searchTracks(query: String) = viewModelScope.launch {
        _tracks.value = Loading
        _tracks.value = searchTracksUseCase(query)
    }
}