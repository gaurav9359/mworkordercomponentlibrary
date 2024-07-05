package com.example.buttonstesing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.componentlibrarydemo.databinding.FragmentTabBinding
import com.example.mworkordercomponentlibrary.components.tabcomponent.TabComponent


class TabFragment : Fragment() {

    private var _binding: FragmentTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeTabs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeTabs() {
        binding.apply {
            configureTab(tabIconDisselectedNormal)
            configureTab(tabIconDisselectedNormalDisabled, disabled = true)

            configureTab(tabIconDisselectedArabic, arabic = true)
            configureTab(tabIconDisselectedArabicDisabled, arabic = true, disabled = true)

            configureTab(tabIconSelectedNormal, selected = true)
            configureTab(tabIconSelectedNormalDisabled, selected = true, disabled = true)

            configureTab(tabIconSelectedArabic, arabic = true, selected = true)
            configureTab(tabIconSelectedArabicDisabled, arabic = true, selected = true, disabled = true)

            configureTab(tabWithoutIconNormal, hideIcon = true)
            configureTab(tabWithoutIconNormalDisabled, hideIcon = true, disabled = true)

            configureTab(tabWithoutIconSelected, hideIcon = true, selected = true)
            configureTab(tabWithoutIconSelectedDisabled, hideIcon = true, selected = true, disabled = true)
        }
    }

    private fun configureTab(
        tab: TabComponent,
        arabic: Boolean = false,
        selected: Boolean = false,
        disabled: Boolean = false,
        hideIcon: Boolean = false
    ) {
        if (arabic) tab.setLayoutDirectionToArabic()
        if (selected) tab.setSelectedTab()
        if (disabled) tab.setDisabled()
        if (hideIcon) tab.hideIcon()

    }
}
