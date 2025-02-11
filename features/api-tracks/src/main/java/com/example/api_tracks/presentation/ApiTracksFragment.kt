package com.example.api_tracks.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.api_tracks.R
import com.example.api_tracks.databinding.FragmentApiTracksBinding
import com.example.api_tracks.presentation.recyclerView.RecyclerAdapter
import com.example.core.api_response.Empty
import com.example.core.api_response.Error
import com.example.core.api_response.Exception
import com.example.core.api_response.Loading
import com.example.core.api_response.Success
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ApiTracksFragment : Fragment() {

    private lateinit var binding: FragmentApiTracksBinding
    private val viewModel: ApiTracksViewModel by viewModels()
    private val adapter: RecyclerAdapter by lazy { RecyclerAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentApiTracksBinding.bind(view)
        setupRecyclerView()
        observeViewModel()
        viewModel.loadTracks()
    }

    private fun observeViewModel() {
        viewModel.tracks.observe(viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.tracks = it.data
                    Log.d(TAG, "Success: ${it.data}")
                }

                is Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    Log.d(TAG, "Loading")
                }

                is Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Error: ${it.message}")
                }

                is Exception -> {
                    Toast.makeText(requireContext(), it.e.message, Toast.LENGTH_SHORT).show()
                }

                is Empty -> {
                    Log.d(TAG, "Empty")
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerViewTracks
        recyclerView.adapter = adapter
        adapter.onTrackItemClickListener = object : RecyclerAdapter.OnTrackItemClickListener {
            override fun onTrackItemCLick(trackId: Int) {
                Log.d(TAG, "onTrackItemCLick: $trackId")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_api_tracks, container, false)
    }

    companion object {
        private const val TAG = "ApiTracksFragmentS"
    }
}