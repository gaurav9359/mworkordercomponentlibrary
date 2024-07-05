package com.example.mworkordercomponentlibrary.adapters

import android.content.Context
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mworkordercomponentlibrary.R
import com.example.mworkordercomponentlibrary.databinding.MediaIssueImageBinding
import java.util.concurrent.TimeUnit

class ImageListAdapter(
    private val context: Context,
    var imageList: List<String>,
    var remainingCount: Int,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    enum class MediaType { IMAGE, VIDEO, UNKNOWN }

    interface OnItemClickListener {
        fun onItemClick(position: Int, mediaType: String)
    }

    class ImageViewHolder(val binding: MediaIssueImageBinding) : RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.imageView
        val overlayText: TextView = binding.overlayText
        val videoLength: TextView = binding.videoLength
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = MediaIssueImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = imageList[position]
        val mediaType = getMediaType(Uri.parse(imageUrl))

        setClickListener(holder, position, mediaType)
        setupViewForMediaType(holder, imageUrl, mediaType)
        setOverlayTextIfNeeded(holder, position)
    }

    override fun getItemCount(): Int = imageList.size

    private fun setClickListener(holder: ImageViewHolder, position: Int, mediaType: MediaType) {
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position, mediaType.toString())
        }
    }

    private fun setupViewForMediaType(holder: ImageViewHolder, imageUrl: String, mediaType: MediaType) {
        when (mediaType) {
            MediaType.IMAGE -> setupImageView(holder, imageUrl)
            MediaType.VIDEO -> setupVideoView(holder, imageUrl)
            MediaType.UNKNOWN -> setupUnknownView(holder)
        }
    }

    private fun setupImageView(holder: ImageViewHolder, imageUrl: String) {
        holder.overlayText.visibility = View.GONE
        holder.videoLength.visibility = View.GONE
        loadImage(holder.imageView, imageUrl)
    }

    private fun setupVideoView(holder: ImageViewHolder, videoUrl: String) {
        holder.videoLength.visibility = View.VISIBLE
        loadVideoThumbnail(holder.imageView, videoUrl)
        setVideoDuration(holder.videoLength, videoUrl)
    }

    private fun setupUnknownView(holder: ImageViewHolder) {
        holder.overlayText.apply {
            visibility = View.VISIBLE
            text = "Unknown"
        }
        holder.videoLength.visibility = View.GONE
        loadImage(holder.imageView, R.drawable.error_image)
    }

    private fun setOverlayTextIfNeeded(holder: ImageViewHolder, position: Int) {
        if (position == imageList.size - 1 && remainingCount > 0) {
            holder.overlayText.apply {
                visibility = View.VISIBLE
                text = "+$remainingCount"
            }
        } else {
            holder.overlayText.visibility = View.GONE
        }
    }

    private fun getMediaType(uri: Uri): MediaType {
        return try {
            when (context.contentResolver.getType(uri)?.split("/")?.firstOrNull()) {
                "image" -> MediaType.IMAGE
                "video" -> MediaType.VIDEO
                else -> MediaType.UNKNOWN
            }
        } catch (e: Exception) {
            Log.e("ImageAdapter", "Error determining media type", e)
            MediaType.UNKNOWN
        }
    }

    private fun loadImage(imageView: ImageView, imageUrl: Any) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.error_image)
            .error(R.drawable.error_image)
            .centerCrop()
            .into(imageView)
    }

    private fun loadVideoThumbnail(imageView: ImageView, videoUrl: String) {
        loadImage(imageView, videoUrl)
    }

    private fun setVideoDuration(textView: TextView, videoUrl: String) {
        try {
            MediaMetadataRetriever().use { retriever ->
                retriever.setDataSource(context, videoUrl.toUri())
                val durationMillis = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLongOrNull() ?: return
                val durationSeconds = TimeUnit.MILLISECONDS.toSeconds(durationMillis)
                textView.text = String.format("%d:%02d", durationSeconds / 60, durationSeconds % 60)
            }
        } catch (e: Exception) {
            Log.e("ImageAdapter", "Error setting video duration", e)
            textView.text = "--:--"
        }
    }
}
