package com.example.presentation.api_tracks

import androidx.fragment.app.viewModels
import com.example.presentation.base_ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ApiTracksFragment : BaseFragment<ApiTracksViewModel>() {
    override val viewModel: ApiTracksViewModel by viewModels()
}