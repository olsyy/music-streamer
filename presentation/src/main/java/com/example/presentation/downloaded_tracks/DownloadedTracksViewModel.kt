package com.example.presentation.downloaded_tracks

import androidx.lifecycle.viewModelScope
import com.example.core.di.LocalRepo
import com.example.domain.usecases.GetTracksUseCase
import com.example.domain.usecases.SearchTracksUseCase
import com.example.presentation.base_ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadedTracksViewModel @Inject constructor(
    @LocalRepo private val getTracksUseCase: GetTracksUseCase,
    @LocalRepo private val searchTracksUseCase: SearchTracksUseCase,
) : BaseViewModel() {

    override fun loadTracks() = viewModelScope.launch {

    }

    override fun searchTracks(query: String) = viewModelScope.launch {

    }
}