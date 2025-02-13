package com.example.presentation.base_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.response.DataResponse
import com.example.domain.entities.Track
import kotlinx.coroutines.Job

abstract class BaseViewModel<T>: ViewModel() {

    protected val _tracks = MutableLiveData<T>()
    val tracks: LiveData<T> = _tracks

    abstract fun loadTracks() : Job
    abstract fun searchTracks(query: String) : Job
}