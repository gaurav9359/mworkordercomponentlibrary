package com.example.buttonstesing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.componentlibrarydemo.R
import com.example.componentlibrarydemo.databinding.FragmentButtonBinding

class ButtonFragment : Fragment() {

    private var _binding: FragmentButtonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentButtonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFlatButtons()
        setupOutlinedButtons()
        setupTransparentButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupFlatButtons() {
        binding.apply {

            flatButton1.apply {
                hideIcon()
            }
            flatButton2.apply {
                setLeadingIcon(R.drawable.add_line)
            }
            flatButton3.apply {
                setTrailingIcon(R.drawable.add_line)
            }
            flatButton4.apply {
                setTrailingIcon(R.drawable.add_line)
                disableButton()
            }
        }
    }

    private fun setupOutlinedButtons() {
        binding.apply {
            outlinedButton1.apply {
                setOutlinedButton("Button")
                hideIcon()
            }
            outlinedButton2.apply {
                setOutlinedButton("Button")
                setLeadingIcon(R.drawable.add_line)
            }
            outlinedButton3.apply {
                setOutlinedButton("Button")
                setTrailingIcon(R.drawable.add_line)
            }
            outlinedButton4.apply {
                setOutlinedButton("Button")
                setTrailingIcon(R.drawable.add_line)
                disableButton()
            }
        }
    }

    private fun setupTransparentButtons() {
        binding.apply {
            transparentButton1.apply {
                setTransparentButton("Button")
                hideIcon()
            }
            transparentButton2.apply {
                setTransparentButton("Button")
                setLeadingIcon(R.drawable.add_line)
            }
            transparentButton3.apply {
                setTransparentButton("Button")
                setTrailingIcon(R.drawable.add_line)
            }
            transparentButton4.apply {
                setTransparentButton("Button")
                setTrailingIcon(R.drawable.add_line)
                disableButton()
            }
        }
    }
}
