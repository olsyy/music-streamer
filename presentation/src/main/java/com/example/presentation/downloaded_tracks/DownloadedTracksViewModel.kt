package com.example.presentation.downloaded_tracks

import androidx.lifecycle.viewModelScope
import com.example.presentation.base_ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadedTracksViewModel @Inject constructor(): BaseViewModel() {


    override fun loadTracks() = viewModelScope.launch {}

    override fun searchTracks(query: String) = viewModelScope.launch {}
}