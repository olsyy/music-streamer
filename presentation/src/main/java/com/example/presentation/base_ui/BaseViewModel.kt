package com.example.presentation.base_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.state.ViewState
import com.example.domain.entities.TracksList
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    protected val _tracks = MutableLiveData<ViewState<TracksList>>()
    val tracks: LiveData<ViewState<TracksList>> = _tracks

    abstract fun loadTracks(): Job
    abstract fun searchTracks(query: String): Job
}