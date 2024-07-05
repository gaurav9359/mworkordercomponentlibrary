package com.example.buttonstesing

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.componentlibrarydemo.databinding.FragmentMediaPickerBinding
import com.example.mworkordercomponentlibrary.components.mediapickercomponent.CaptureIssue


class MediaPickerFragment : Fragment() {

    private lateinit var binding: FragmentMediaPickerBinding
    private lateinit var captureIssue: CaptureIssue
    private val imageList: MutableList<String> = mutableListOf()

    private lateinit var captureIssueArabic: CaptureIssue
    private val imageListArabic: MutableList<String> = mutableListOf()


    private val pickMediaLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val data = result.data
            val selectedMediaUri = captureIssue.onActivityResult(CaptureIssue.PICK_MEDIA_REQUEST_CODE, result.resultCode, data, null)
            selectedMediaUri?.let {
                imageList.add(it.toString())
                captureIssue.displayImage(imageList)
            }
        }
    }
    private val pickMediaLauncherArabic = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val data = result.data
            val selectedMediaUriArabic = captureIssueArabic.onActivityResult(CaptureIssue.PICK_MEDIA_REQUEST_CODE, result.resultCode, data, null)
            selectedMediaUriArabic?.let {
                imageListArabic.add(it.toString())
                captureIssueArabic.displayImage(imageListArabic)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMediaPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCaptureIssue()
    }

    private fun setupCaptureIssue() {
        captureIssue = binding.captureIssue
        captureIssue.displayImage(imageList)
        captureIssueArabic = binding.captureIssueArabic
        captureIssueArabic.arabic()
        captureIssueArabic.displayImage(imageListArabic)

        captureIssue.setAddMediaClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/* video/*"
            pickMediaLauncher.launch(intent)
        }
        captureIssueArabic.setAddMediaClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/* video/*"
            pickMediaLauncherArabic.launch(intent)
        }
    }



}