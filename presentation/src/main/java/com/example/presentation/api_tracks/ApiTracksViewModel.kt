package com.example.presentation.api_tracks

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.core.response.Loading
import com.example.core.di.RemoteRepo
import com.example.domain.usecases.GetTracksUseCase
import com.example.domain.usecases.SearchTracksUseCase
import com.example.presentation.base_ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiTracksViewModel @Inject constructor(
    @RemoteRepo private val getTracksUseCase: GetTracksUseCase,
    @RemoteRepo private val searchTracksUseCase: SearchTracksUseCase,
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