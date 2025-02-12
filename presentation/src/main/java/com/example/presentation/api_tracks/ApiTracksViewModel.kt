package com.example.presentation.api_tracks

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.api_response.Loading
import com.example.domain.usecases.GetTracksUseCase
import com.example.domain.usecases.SearchTracksUseCase
import com.example.presentation.base_ui.BaseViewModel
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
        Log.d("TAGsTracks", "searchTracksQuery: $query")
        _tracks.value = searchTracksUseCase(query)
    }
}