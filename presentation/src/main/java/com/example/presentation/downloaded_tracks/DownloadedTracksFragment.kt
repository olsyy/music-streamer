package com.example.presentation.downloaded_tracks

import android.util.Log
import androidx.fragment.app.viewModels
import com.example.presentation.api_tracks.ApiTracksViewModel
import com.example.presentation.base_ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class DownloadedTracksFragment : BaseFragment<DownloadedTracksViewModel>() {
    override val viewModel: DownloadedTracksViewModel by viewModels()

    override fun setupObservers() {
        Log.d("TAGsTracks", "setupObservers: $viewModel")
    }
}