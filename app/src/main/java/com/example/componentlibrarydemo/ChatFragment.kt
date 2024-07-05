package com.example.buttonstesing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.componentlibrarydemo.databinding.FragmentChatBinding

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVariants()

    }

    private fun setupVariants() {
        setupVariant1()
        setupVariant2()
        setupVariant3()
        setupVariant1Arabic()
        setupVariant2Arabic()
        setupVariant3Arabic()
        setupVariant1Ltr()
        setupVariant1ArabicLtr()
    }

    private fun setupVariant1() {
        binding.chatVariant1.apply {
            card1Clicked { sendToast("card1 clicked") }
            card2Clicked { sendToast("card2 clicked") }
        }
    }

    private fun setupVariant2() {
        binding.chatVariant2.apply {
            card1Clicked { sendToast("card1 clicked") }
            card2Clicked { sendToast("card2 clicked") }
            card3Clicked { sendToast("card3 clicked") }
            imageClicked { sendToast("chat Image Clicked") }
        }
    }

    private fun setupVariant3() {
        binding.chatVariant3.apply {
            hideCard3(true)
            card1Clicked { sendToast("card1 clicked") }
            card2Clicked { sendToast("card2 clicked") }
            imageClicked { sendToast("chat Image Clicked") }
        }
    }

    private fun setupVariant1Arabic() {
        binding.chatVariant1Arabic.apply {
            card1Clicked { sendToast("card1 clicked") }
            card2Clicked { sendToast("card2 clicked") }
            arabic()
        }
    }

    private fun setupVariant2Arabic() {
        binding.chatVariant2Arabic.apply {
            card1Clicked { sendToast("card1 clicked") }
            card2Clicked { sendToast("card2 clicked") }
            card3Clicked { sendToast("card3 clicked") }
            imageClicked { sendToast("chat Image Clicked") }
            arabic()
        }
    }

    private fun setupVariant3Arabic() {
        binding.chatVariant3Arabic.apply {
            hideCard3(true)
            card1Clicked { sendToast("card1 clicked") }
            card2Clicked { sendToast("card2 clicked") }
            imageClicked { sendToast("chat Image Clicked") }
            arabic()
        }
    }

    private fun setupVariant1Ltr() {
        binding.variant1Ltr.apply {
            getLtr()
            card1Clicked { sendToast("card1 clicked") }
            card2Clicked { sendToast("card2 clicked") }
        }
    }

    private fun setupVariant1ArabicLtr() {
        binding.variant1ArabicLtr.apply {
            arabic_ltr()
            card1Clicked { sendToast("card1 clicked") }
            card2Clicked { sendToast("card2 clicked") }
        }
    }


    private fun sendToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
