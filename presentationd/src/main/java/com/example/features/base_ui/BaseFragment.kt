package com.example.features.base_ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.core.api_response.Empty
import com.example.core.api_response.Error
import com.example.core.api_response.Exception
import com.example.core.api_response.Loading
import com.example.core.api_response.Success
import com.example.features.base_ui.recyclerView.RecyclerAdapter
import com.example.features.R

abstract class BaseFragment< VB : ViewBinding, VM : BaseViewModel>(
    private val inflateBinding: (
        inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean,
    ) -> VB,
) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    protected abstract val viewModel: VM
    protected val adapter: RecyclerAdapter by lazy { RecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = inflateBinding(inflater, container, false)

        setupAdapter()
        setupObservers()
        setupListeners()
        viewModel.loadTracks()

        return binding.root
    }

    protected fun setupAdapter() {
        val recyclerView = binding.root.findViewById<RecyclerView>(R.id.recyclerViewTracks)
        recyclerView.adapter = adapter
    }

    protected fun setupListeners() {
        adapter.onTrackItemClickListener = object : RecyclerAdapter.OnTrackItemClickListener {
            override fun onTrackItemCLick(trackId: Long) {
                Log.d(TAG, "onTrackItemCLick")
            }
        }
    }

    protected fun setupObservers() {
        val progressBar = binding.root.findViewById<ProgressBar>(R.id.progressBar)
        viewModel.tracks.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Success -> {
                    progressBar.visibility = View.GONE
                    adapter.tracks = response.data
                }

                is Loading -> {
                    progressBar.visibility = View.VISIBLE
                }

                is Error -> {
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_SHORT).show()
                }

                is Exception -> {
                    Toast.makeText(requireContext(), response.e.message, Toast.LENGTH_SHORT).show()
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