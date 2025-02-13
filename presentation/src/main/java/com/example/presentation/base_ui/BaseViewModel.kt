package com.example.presentation.base_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.response.Response
import com.example.domain.entities.Track
import kotlinx.coroutines.Job

abstract class BaseViewModel: ViewModel() {

    protected val _tracks = MutableLiveData<Response<List<Track>>>()
    val tracks: LiveData<Response<List<Track>>> = _tracks

    abstract fun loadTracks() : Job
    abstract fun searchTracks(query: String) : Job
}