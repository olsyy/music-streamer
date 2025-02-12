package com.example.features.base_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.api_response.ApiResponse
import com.example.shared_domain.entities.Track
import kotlinx.coroutines.Job

abstract class BaseViewModel: ViewModel() {

    protected val _tracks = MutableLiveData<ApiResponse<List<Track>>>()
    val tracks: LiveData<ApiResponse<List<Track>>> = _tracks

    abstract fun loadTracks() : Job
    abstract fun searchTracks(query: String) : Job
}