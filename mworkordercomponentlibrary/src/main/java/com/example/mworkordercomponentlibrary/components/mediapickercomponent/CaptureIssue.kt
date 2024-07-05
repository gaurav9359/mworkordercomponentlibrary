package com.example.mworkordercomponentlibrary.components.mediapickercomponent

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mworkordercomponentlibrary.R
import com.example.mworkordercomponentlibrary.adapters.ImageListAdapter
import com.example.mworkordercomponentlibrary.databinding.MediaCaptureIssueBinding

class CaptureIssue @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ImageListAdapter.OnItemClickListener {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val binding: MediaCaptureIssueBinding = MediaCaptureIssueBinding.inflate(inflater, this, true)
    lateinit var adapter: ImageListAdapter

    companion object {
        const val PICK_MEDIA_REQUEST_CODE = 101
    }

    init {
        setupViews()
    }

    /**
     * Displays a list of images in the view.
     *
     * This function calculates the number of images that can fit in the layout
     * and displays them along with a count of remaining images.
     *
     * @param imageList The list of image URLs to display.
     */
    fun displayImage(imageList: List<String>) {
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                val layoutWidth = width
                val imageWidthDp = 72
                val layoutWidthDp = (layoutWidth / resources.displayMetrics.density) - 20
                val numberOfImages = (layoutWidthDp / imageWidthDp).toInt() - 2
                val remainingCount = imageList.size - numberOfImages
                showImages(imageList.take(numberOfImages), remainingCount)
            }
        })
    }

    /**
     * Hides the "Add Media" button.
     */
    fun hideAddMedia() {
        binding.addMedia.visibility = View.GONE
    }

    /**
     * Sets the layout direction to right-to-left for Arabic language support.
     */
    fun arabic() {
        binding.captureIssueLayout.layoutDirection = View.LAYOUT_DIRECTION_RTL
    }

    /**
     * Handles the result of media selection activity.
     *
     * @param requestCode The request code for the activity result.
     * @param resultCode The result code of the activity.
     * @param data The intent data returned from the activity.
     * @param selectedMediaUri The URI of the selected media (optional).
     * @return The URI of the selected media, or null if no media was selected.
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, selectedMediaUri: Uri? = null): Uri? {
        if (requestCode == PICK_MEDIA_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val uri: Uri? = selectedMediaUri ?: data.data
            return uri
        }
        return null
    }

    /**
     * Sets a click listener for the "Add Media" button.
     *
     * @param listener The function to be called when the button is clicked.
     */
    fun setAddMediaClickListener(listener: () -> Unit) {
        binding.addMedia.setOnClickListener { listener() }
    }

    /**
     * Handles clicks on items in the image list.
     *
     * @param position The position of the clicked item.
     * @param mediaType The type of media that was clicked.
     */
    override fun onItemClick(position: Int, mediaType: String) {
        Toast.makeText(context, "$position item clicked", Toast.LENGTH_SHORT).show()

    }

    /**
     * Updates the adapter with the provided image list and remaining count.
     *
     * @param imageList The list of image URLs to display.
     * @param remainingCount The number of images not displayed.
     */
    private fun showImages(imageList: List<String>, remainingCount: Int) {
        adapter.imageList = imageList
        adapter.remainingCount = remainingCount
        adapter.notifyDataSetChanged()
    }

    /**
     * Sets up the RecyclerView and its adapter.
     */
    private fun setupViews() {
        val recyclerView: RecyclerView = findViewById(R.id.issue_image_rv)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ImageListAdapter(context, listOf(), 0, this)
        recyclerView.adapter = adapter
    }
}