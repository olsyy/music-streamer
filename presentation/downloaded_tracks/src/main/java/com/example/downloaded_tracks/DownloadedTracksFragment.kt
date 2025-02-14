package com.example.downloaded_tracks

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.example.shared_ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadedTracksFragment : BaseFragment<DownloadedTracksViewModel>() {
    override val viewModel: DownloadedTracksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission()
    }

    private fun requestPermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_AUDIO
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        val requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.d("DownloadedTracksFragmentS", "Fragment downloaded tracks is destroyed")
                viewModel.loadTracks()
            } else {
                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
        requestPermissionLauncher.launch(permission)
    }

    override fun onDestroy() {
        Log.d("DownloadedTracksFragmentS", "Fragment downloaded tracks is destroyed")
        super.onDestroy()
    }
}