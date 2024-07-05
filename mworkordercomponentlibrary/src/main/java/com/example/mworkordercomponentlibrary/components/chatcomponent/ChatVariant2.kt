package com.example.mworkordercomponentlibrary.components.chatcomponent

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.mworkordercomponentlibrary.R
import com.example.mworkordercomponentlibrary.databinding.ChatVariant2Binding

class ChatVariant2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val inflater: LayoutInflater = LayoutInflater.from(context)
    val binding: ChatVariant2Binding = ChatVariant2Binding.inflate(inflater, this, true)

    /**
     * Sets the title of the chat variant.
     *
     * @param newTitle The new title to be set.
     */
    fun setTitle(newTitle: String) {
        binding.issueTitle.text = newTitle
    }

    /**
     * Sets the breakdown information with an optional icon.
     *
     * @param breakdownIcon The resource ID of the breakdown icon (default is -1 for no icon).
     * @param breakdownText The text describing the breakdown.
     */
    fun setBreakDown(@DrawableRes breakdownIcon: Int = -1, breakdownText: String) {
        if (breakdownIcon != -1) {
            binding.breakdownIcon.setBackgroundResource(breakdownIcon)
        }
        binding.breakdownText.text = breakdownText
    }

    /**
     * Sets the priority text.
     *
     * @param priority The priority text to be set.
     */
    fun setPriority(priority: String) {
        binding.priorityText.text = priority
    }

    /**
     * Sets the impact text.
     *
     * @param impact The impact text to be set.
     */
    fun setImpact(impact: String) {
        binding.impactText.text = impact
    }

    /**
     * Sets the probability information with an optional icon.
     *
     * @param probabilityIcon The resource ID of the probability icon (default is -1 for no icon).
     * @param probabilityText The text describing the probability.
     */
    fun setProbability(@DrawableRes probabilityIcon: Int = -1, probabilityText: String) {
        if (probabilityIcon != -1) {
            binding.probabilityIcon.setBackgroundResource(probabilityIcon)
        }
        binding.probabilityText.text = probabilityText
    }

    /**
     * Sets the description text.
     *
     * @param description The description text to be set.
     */
    fun setDescription(description: String) {
        binding.description.text = description
    }

    /**
     * Sets the title for card 1.
     *
     * @param newCardTitle The new title for card 1.
     */
    fun setCard1Title(newCardTitle: String) {
        binding.card1.card1DocumentTitle.text = newCardTitle
    }

    /**
     * Sets the subtitle for card 1.
     *
     * @param newCardSubtitle The new subtitle for card 1.
     */
    fun setCard1Subtitle(newCardSubtitle: String) {
        binding.card1.card1Subtitle.text = newCardSubtitle
    }

    /**
     * Sets the icon for card 1.
     *
     * @param cardIcon The resource ID of the icon for card 1.
     */
    fun setCard1Icon(@DrawableRes cardIcon: Int) {
        binding.card1.icon.setBackgroundResource(cardIcon)
    }

    /**
     * Sets the title for card 2.
     *
     * @param newCardTitle The new title for card 2.
     */
    fun setCard2Title(newCardTitle: String) {
        binding.card2.card1DocumentTitle.text = newCardTitle
    }

    /**
     * Sets the subtitle for card 2.
     *
     * @param newCardSubtitle The new subtitle for card 2.
     */
    fun setCard2Subtitle(newCardSubtitle: String) {
        binding.card1.card1Subtitle.text = newCardSubtitle
    }

    /**
     * Sets the icon for card 2.
     *
     * @param cardIcon The resource ID of the icon for card 2.
     */
    fun setCard2Icon(@DrawableRes cardIcon: Int) {
        binding.card1.icon.setBackgroundResource(cardIcon)
    }

    /**
     * Sets the chat media image and handles the display of remaining image count.
     *
     * @param imageToShow The URL of the image to be displayed.
     * @param remainingImageCount The number of remaining images (default is -1 for no remaining images).
     */
    fun setChatMedia(imageToShow: String, remainingImageCount: Int = -1) {
        if (imageToShow.isNotEmpty()) {
            loadImage(binding.issueImage, imageToShow)
            if (remainingImageCount > 0) {
                val remainingImages = remainingImageCount
                binding.imageQtyText.text = "+$remainingImages"
                binding.imageQtyText.visibility = View.VISIBLE
            } else {
                binding.imageQtyText.visibility = View.GONE
            }
        } else {
            binding.issueImage.setImageResource(R.drawable.error_image)
            binding.imageQtyText.visibility = View.GONE
        }
    }

    /**
     * Sets the image for card 2.
     *
     * @param imageToShow The URL of the image to be displayed in card 2.
     */
    fun setcard2Image(imageToShow: String) {
        loadImage(binding.card2.image, imageToShow)
    }

    /**
     * Sets the title for card 3.
     *
     * @param newCardTitle The new title for card 3.
     */
    fun setCard3Title(newCardTitle: String) {
        binding.card3.card1DocumentTitle.text = newCardTitle
    }

    /**
     * Sets the subtitle for card 3.
     *
     * @param newCardSubtitle The new subtitle for card 3.
     */
    fun setCard3Subtitle(newCardSubtitle: String) {
        binding.card3.card1Subtitle.text = newCardSubtitle
    }

    /**
     * Sets the icon for card 3.
     *
     * @param cardIcon The resource ID of the icon for card 3.
     */
    fun setCard3Icon(@DrawableRes cardIcon: Int) {
        binding.card3.icon.setBackgroundResource(cardIcon)
    }

    /**
     * Sets the image for card 3.
     *
     * @param imageToShow The URL of the image to be displayed in card 3.
     */
    fun setcard3Image(imageToShow: String) {
        loadImage(binding.card3.image, imageToShow)
    }

    /**
     * Hides or shows card 1.
     *
     * @param hidden True to hide card 1, false to show it.
     */
    fun hideCard1(hidden: Boolean) {
        if (hidden) {
            binding.card1.cardLayout.visibility = View.GONE
        }
    }

    /**
     * Hides or shows card 2 and its associated line.
     *
     * @param hidden True to hide card 2 and its line, false to show them.
     */
    fun hideCard2(hidden: Boolean) {
        if (hidden) {
            binding.card2.imageCardLayout.visibility = View.GONE
            binding.card1Line.visibility = View.GONE
        }
    }

    /**
     * Hides or shows card 3 and its associated line.
     *
     * @param hidden True to hide card 3 and its line, false to show them.
     */
    fun hideCard3(hidden: Boolean) {
        if (hidden) {
            binding.card3.imageCardLayout.visibility = View.GONE
            binding.card2Line.visibility = View.GONE
        }
    }

    /**
     * Sets a click listener for the chat image.
     *
     * @param callback The function to be called when the image is clicked.
     */
    fun imageClicked(callback: () -> Unit) {
        binding.chatImage.setOnClickListener {
            callback()
        }
    }

    private fun loadImage(imageView: ImageView, url: String) {
        Glide.with(context)
            .load(url)
            .placeholder(R.drawable.chat_media)
            .error(R.drawable.error_image)
            .into(imageView)
    }

    private fun dpToPx(dp: Int, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    /**
     * Sets a click listener for the chat image.
     *
     * @param callback The function to be called when the image is clicked.
     */
    fun onImageClicked(callback: () -> Unit) {
        binding.chatImage.setOnClickListener {
            callback()
        }
    }

    /**
     * Sets a click listener for card 1.
     *
     * @param callback The function to be called when card 1 is clicked.
     */
    fun card1Clicked(callback: () -> Unit) {
        binding.card1.cardLayout.setOnClickListener {
            callback()
        }
    }

    /**
     * Sets a click listener for card 2.
     *
     * @param callback The function to be called when card 2 is clicked.
     */
    fun card2Clicked(callback: () -> Unit) {
        binding.card2.imageCardLayout.setOnClickListener {
            callback()
        }
    }

    /**
     * Sets a click listener for card 3.
     *
     * @param callback The function to be called when card 3 is clicked.
     */
    fun card3Clicked(callback: () -> Unit) {
        binding.card3.imageCardLayout.setOnClickListener {
            callback()
        }
    }

    /**
     * Applies right-to-left (RTL) layout styling for Arabic language support.
     */
    fun arabic() {
        binding.variant2MainTitle.layoutDirection = View.LAYOUT_DIRECTION_RTL
        binding.variant2MainTitle.setBackgroundResource(R.drawable.three_end_rounded_corner_arabic)
        binding.description.gravity = Gravity.END
    }
}