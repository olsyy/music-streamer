package com.example.api_tracks.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api_tracks.domain.entities.TracksList
import com.example.api_tracks.domain.usecases.GetTracksUseCase
import com.example.api_tracks.domain.usecases.SearchTracksUseCase
import com.example.core.api_response.ApiResponse
import com.example.core.api_response.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiTracksViewModel @Inject constructor(
    private val getTracksUseCase: GetTracksUseCase,
    private val searchTracksUseCase: SearchTracksUseCase,
) : ViewModel() {

    private val _tracks = MutableLiveData<ApiResponse<TracksList>>()
    val tracks: LiveData<ApiResponse<TracksList>>
        get() = _tracks

    init {
        _tracks.value = Loading
    }

    fun loadTracks() = viewModelScope.launch {
        _tracks.value = Loading
        _tracks.value = getTracksUseCase()
    }
}