package com.example.presentation.api_tracks

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.core.response.Error
import com.example.core.response.Exception
import com.example.core.response.Loading
import com.example.core.response.Success
import com.example.presentation.base_ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ApiTracksFragment : BaseFragment<ApiTracksViewModel>() {
    override val viewModel: ApiTracksViewModel by viewModels()

    override fun setupObservers() {
        viewModel.tracks.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.tracks = response.data
                    if (response.data.isEmpty()) {
                        Toast.makeText(
                            context,
                            "No tracks found for your query.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                is Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Error -> {
                    Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                }

                is Exception -> {
                    Toast.makeText(context, response.e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}