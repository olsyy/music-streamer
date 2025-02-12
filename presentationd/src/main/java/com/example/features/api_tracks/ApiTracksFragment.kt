package com.example.features.api_tracks

import androidx.fragment.app.viewModels
import com.example.features.base_ui.BaseFragment
import com.example.features.databinding.FragmentBaseBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApiTracksFragment :
    BaseFragment<FragmentBaseBinding, ApiTracksViewModel>(FragmentBaseBinding::inflate) {
    override val viewModel: ApiTracksViewModel by viewModels()
}