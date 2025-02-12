package com.example.presentation.base_ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.core.api_response.Error
import com.example.core.api_response.Exception
import com.example.core.api_response.Loading
import com.example.core.api_response.Success
import com.example.presentation.R
import com.example.presentation.base_ui.recyclerView.RecyclerAdapter
import com.example.presentation.databinding.FragmentBaseBinding

abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    init {
        Log.d(TAG, "Init BaseFragment")
    }

    private var _binding: FragmentBaseBinding? = null
    protected val binding: FragmentBaseBinding get() = _binding!!

    protected abstract val viewModel: VM
    protected val adapter: RecyclerAdapter by lazy { RecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentBaseBinding.inflate(inflater, container, false)

        setupAdapter()
        setupObservers()
        setupTrackItemListener()
        setupSearchListener()

        viewModel.loadTracks()

        return binding.root
    }


    protected fun setupAdapter() {
        val recyclerView = binding.root.findViewById<RecyclerView>(R.id.recyclerViewTracks)
        recyclerView.adapter = adapter
    }

    protected fun setupTrackItemListener() {
        adapter.onTrackItemClickListener = object : RecyclerAdapter.OnTrackItemClickListener {
            override fun onTrackItemCLick(trackId: Long) {
                Log.d(TAG, "onTrackItemCLick")
            }
        }
    }

    protected fun setupSearchListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchTracks(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    protected fun setupObservers() {
        viewModel.tracks.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.tracks = response.data
                    if (response.data.isEmpty()) {
                        Toast.makeText(context, "No tracks found for your query.", Toast.LENGTH_SHORT).show()
                    }
                }

                is Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Error -> {
                    Toast.makeText(context, response.message, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Error: ${response.message}")
                }

                is Exception -> {
                    Toast.makeText(context, response.e.message, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Exception: ${response.e.message}")
                }
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