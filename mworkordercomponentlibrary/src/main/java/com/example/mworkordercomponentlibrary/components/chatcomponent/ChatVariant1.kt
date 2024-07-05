package com.example.mworkordercomponentlibrary.components.chatcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.mworkordercomponentlibrary.R
import com.example.mworkordercomponentlibrary.databinding.ChatVariant1Binding


class ChatVariant1 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val inflater: LayoutInflater
    val binding: ChatVariant1Binding

    init {
        inflater = LayoutInflater.from(context)
        binding = ChatVariant1Binding.inflate(inflater, this, true)
    }

    /**
     * Sets the label text for the chat variant.
     *
     * @param labelText The text to be set as the label.
     */
    fun setLabel(labelText: String) {
        binding.label.text = labelText
    }

    /**
     * Sets the chat title text.
     *
     * @param titleText The text to be set as the chat title.
     */
    fun setChatTitle(titleText: String) {
        binding.chatTitle.text = titleText
    }

    /**
     * Sets the sub-label for document 1 header.
     *
     * @param newSubLabel The new sub-label text.
     */
    fun setDocument1HeaderSubLabel(newSubLabel: String) {
        binding.document1Header.card1Subtitle.text = newSubLabel
    }

    /**
     * Sets the label for document 1 header.
     *
     * @param newLabel The new label text.
     */
    fun setDocument1HeaderLabel(newLabel: String) {
        binding.document1Header.card1DocumentTitle.text = newLabel
    }

    /**
     * Sets the icon for document 1.
     *
     * @param newIcon The resource ID of the new icon drawable.
     */
    fun setDocument1Icon(@DrawableRes newIcon: Int) {
        binding.document1Header.icon.setBackgroundResource(newIcon)
    }

    /**
     * Sets the sub-label for document 2 header.
     *
     * @param newSubLabel The new sub-label text.
     */
    fun setDocument2HeaderSubLabel(newSubLabel: String) {
        binding.document1Header.card1Subtitle.text = newSubLabel
    }

    /**
     * Sets the label for document 2 header.
     *
     * @param newLabel The new label text.
     */
    fun setDocument2HeaderLabel(newLabel: String) {
        binding.document1Header.card1DocumentTitle.text = newLabel
    }

    /**
     * Sets the icon for document 2.
     *
     * @param newIcon The resource ID of the new icon drawable.
     */
    fun setDocument2Icon(@DrawableRes newIcon: Int) {
        binding.document1Header.icon.setBackgroundResource(newIcon)
    }

    /**
     * Sets a click listener for card 1.
     *
     * @param callback The function to be called when card 1 is clicked.
     */
    fun card1Clicked(callback: () -> Unit) {
        binding.document1Header.cardLayout.setOnClickListener {
            callback()
        }
    }

    /**
     * Sets a click listener for card 2.
     *
     * @param callback The function to be called when card 2 is clicked.
     */
    fun card2Clicked(callback: () -> Unit) {
        binding.document2Header.cardLayout.setOnClickListener {
            callback()
        }
    }

    /**
     * Applies left-to-right (LTR) layout styling.
     */
    fun getLtr() {
        val colorValue = 0xFFDEDAF8.toInt()
        val drawableBackground = ContextCompat.getDrawable(context, R.drawable.three_end_rounded_corner_arabic)

        if (drawableBackground != null) {
            val mutatedDrawable = DrawableCompat.wrap(drawableBackground).mutate()
            DrawableCompat.setTint(mutatedDrawable, colorValue)

            binding.variant1MainTitle.background = mutatedDrawable
        }
    }

    /**
     * Applies right-to-left (RTL) layout styling for Arabic language support.
     */
    fun arabic() {
        binding.variant1MainTitle.layoutDirection = View.LAYOUT_DIRECTION_RTL
        binding.variant1MainTitle.setBackgroundResource(R.drawable.three_end_rounded_corner_arabic)
        binding.chatTitle.gravity = Gravity.END
    }

    /**
     * Applies a combination of right-to-left (RTL) layout direction with left-to-right (LTR) specific styling.
     */
    fun arabic_ltr() {
        binding.variant1MainTitle.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val colorValue = 0xFFDEDAF8.toInt()
        val drawableBackground = ContextCompat.getDrawable(context, R.drawable.three_end_rounded_corner)

        if (drawableBackground != null) {
            val mutatedDrawable = DrawableCompat.wrap(drawableBackground).mutate()
            DrawableCompat.setTint(mutatedDrawable, colorValue)

            binding.variant1MainTitle.background = mutatedDrawable
        }

        binding.chatTitle.gravity = Gravity.END
    }
}