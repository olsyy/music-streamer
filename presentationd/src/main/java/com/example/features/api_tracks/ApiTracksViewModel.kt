package com.example.features.api_tracks

import androidx.lifecycle.viewModelScope
import com.example.core.api_response.Loading
import com.example.shared_domain.usecases.GetTracksUseCase
import com.example.shared_domain.usecases.SearchTracksUseCase
import com.example.features.base_ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiTracksViewModel @Inject constructor(
    private val getTracksUseCase: GetTracksUseCase,
    private val searchTracksUseCase: SearchTracksUseCase,
) : BaseViewModel() {

    init {
        _tracks.value = Loading
    }

    override fun loadTracks() = viewModelScope.launch {
        _tracks.value = getTracksUseCase()
    }

    override fun searchTracks(query: String) = viewModelScope.launch {
        _tracks.value = searchTracksUseCase(query)
    }
}