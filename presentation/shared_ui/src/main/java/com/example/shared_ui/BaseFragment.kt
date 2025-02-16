package com.example.shared_ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.core.state.Empty
import com.example.core.state.Error
import com.example.core.state.Exception
import com.example.core.state.Loading
import com.example.core.state.PlaybackSource
import com.example.core.state.Success
import com.example.shared_ui.databinding.FragmentBaseBinding
import com.example.shared_ui.recyclerView.RecyclerAdapter

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    private var _binding: FragmentBaseBinding? = null
    private val binding: FragmentBaseBinding get() = _binding!!

    protected abstract val viewModel: VM
    private val adapter: RecyclerAdapter by lazy { RecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentBaseBinding.inflate(inflater, container, false)

        setupAdapter()
        setupObservers()
        setupTrackItemListener()
        setupSearchListener()
        Log.d("BaseFragmentS", "Init BaseFragment")

        return binding.root
    }

    private fun setupAdapter() {
        val recyclerView = binding.root.findViewById<RecyclerView>(R.id.recyclerViewTracks)
        recyclerView.adapter = adapter
    }

    private fun setupTrackItemListener() {
        adapter.onTrackItemClickListener = object : RecyclerAdapter.OnTrackItemClickListener {
            override fun onTrackItemCLick(trackId: Long) {
                val playbackSource = getPlaybackSource()
                val allTracks = ArrayList(adapter.tracks)
                val navArgs = bundleOf(
                    "trackId" to trackId,
                    "playbackSource" to playbackSource.name,
                    "allTracks" to allTracks,
                )

                findNavController().navigate(
                    com.example.navigation.R.id.playbackFragment,
                    navArgs
                )
            }
        }
    }

    protected abstract fun getPlaybackSource(): PlaybackSource

    private fun setupSearchListener() {
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchTracks(it)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    viewModel.loadTracks()
                }
                return true
            }
        })
    }

    private fun setupObservers() {
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

                is Empty -> {}
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val TAG = "BaseFragmentS"
    }
}