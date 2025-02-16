package com.example.shared_ui.recyclerView

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entities.Track
import com.example.shared_ui.R

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var tracks = listOf<Track>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onTrackItemClickListener: OnTrackItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.track_item,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        val track = tracks[position]
        with(holder) {
            loadImage(holder, track.cover)
            trackTitle.text = track.title
            trackArtist.text = track.artist
            itemView.setOnClickListener {
                onTrackItemClickListener?.onTrackItemCLick(track.id)
            }
        }
    }

    private fun loadImage(holder: ViewHolder, url: Uri?) = Glide.with(holder.view)
        .load(url)
        .placeholder(R.drawable.base_image_track_cover)
        .error(R.drawable.base_image_track_cover)
        .into(holder.trackCover)

    override fun getItemCount() = tracks.size

    interface OnTrackItemClickListener {
        fun onTrackItemCLick(trackId: Long)
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val trackCover: ImageView = view.findViewById(R.id.imageView)
        val trackTitle: TextView = view.findViewById(R.id.textView1)
        val trackArtist: TextView = view.findViewById(R.id.textView2)
    }
}