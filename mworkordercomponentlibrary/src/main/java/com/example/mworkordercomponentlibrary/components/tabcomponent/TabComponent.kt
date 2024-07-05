package com.example.mworkordercomponentlibrary.components.tabcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.mworkordercomponentlibrary.R
import com.example.mworkordercomponentlibrary.databinding.TabComponentBinding

class TabComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val binding: TabComponentBinding = TabComponentBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var isTabEnabled: Boolean = true
    private var isTabSelected: Boolean = false

    /**
     * Sets the text of the tab.
     *
     * @param newText The new text to be displayed on the tab.
     */
    fun setText(newText: String) {
        binding.customTab.text = newText
    }

    /**
     * Sets the icon of the tab.
     *
     * @param newIcon The resource ID of the new icon to be displayed on the tab. Default value is -1 (no icon).
     */
    fun setIcon(@DrawableRes newIcon: Int = -1) {
        if (newIcon != -1) {
            binding.customTab.icon = ContextCompat.getDrawable(context, newIcon)
        }
    }

    /**
     * Sets a click listener for the tab.
     *
     * @param callback The function to be called when the tab is clicked (if it's enabled).
     */
    fun onTabClick(callback: () -> Unit) {
        if (isTabEnabled) callback()
    }

    /**
     * Sets the layout direction of the tab to right-to-left (RTL) for Arabic support.
     */
    fun setLayoutDirectionToArabic() {
        binding.customTab.layoutDirection = View.LAYOUT_DIRECTION_RTL
    }

    /**
     * Sets the selected state of the tab.
     *
     * @param select Whether the tab should be selected or not. Default is true.
     */
    fun setSelectedTab(select: Boolean = true) {
        isTabSelected = select
        updateState()
    }

    /**
     * Hides the icon of the tab and removes its padding.
     */
    fun hideIcon() {
        binding.customTab.icon = null
        binding.customTab.iconPadding = 0
    }

    /**
     * Sets the disabled state of the tab.
     *
     * @param disable Whether the tab should be disabled or not. Default is true.
     */
    fun setDisabled(disable: Boolean = true) {
        isTabEnabled = !disable
        updateState()
    }

    /**
     * Updates the visual state of the tab based on its current enabled and selected states.
     */
    private fun updateState() {
        when {
            !isTabEnabled -> setDisabledState()
            isTabSelected -> setSelectedState()
            else -> setDefaultState()
        }
    }

    /**
     * Applies the visual style for the disabled state of the tab.
     */
    private fun setDisabledState() {
        binding.customTab.background = getDisabledBackground()
        binding.customTab.setTextColor(ContextCompat.getColor(context, R.color.tab_disabled))
        binding.customTab.iconTint = ContextCompat.getColorStateList(context,
            R.color.tab_disabled_icon
        )
    }

    /**
     * Applies the visual style for the selected state of the tab.
     */
    private fun setSelectedState() {
        binding.customTab.background = ContextCompat.getDrawable(context,
            R.drawable.tab_selected_background_selector
        )
        binding.customTab.setTextColor(ContextCompat.getColor(context, R.color.tab_primary_color))
        binding.customTab.iconTint = ContextCompat.getColorStateList(context,
            R.color.tab_primary_color
        )
    }

    /**
     * Applies the visual style for the default (unselected) state of the tab.
     */
    private fun setDefaultState() {
        binding.customTab.background = ContextCompat.getDrawable(context,
            R.drawable.tab_disselected_background_selector
        )
        binding.customTab.setTextColor(ContextCompat.getColor(context, R.color.tab_primary_color))
        binding.customTab.iconTint = ContextCompat.getColorStateList(context,
            R.color.tab_primary_color
        )
    }

    /**
     * Returns the appropriate background drawable for the disabled state of the tab.
     *
     * @return The background drawable for the disabled state.
     */
    private fun getDisabledBackground() =
        if (isTabSelected) {
            ContextCompat.getDrawable(context, R.drawable.tab_disabled_background)
        } else {
            ContextCompat.getDrawable(context, android.R.color.transparent)
        }
}
