package com.example.api_tracks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.shared_ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ApiTracksFragment : BaseFragment<ApiTracksViewModel>() {
    override val viewModel: ApiTracksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadTracks()
    }
}