package com.example.mworkordercomponentlibrary.components.buttoncomponent

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.mworkordercomponentlibrary.R
import com.example.mworkordercomponentlibrary.databinding.CustomButtonBinding
import com.google.android.material.button.MaterialButton

class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    /** Enum class defining the available button types. */
    enum class ButtonTypes {
        FLAT, OUTLINED, TRANSPARENT
    }

    private val binding: CustomButtonBinding =
        CustomButtonBinding.inflate(LayoutInflater.from(context), this, true)
    private var currentButtonType: ButtonTypes = ButtonTypes.FLAT
    private var isEnabled = true

    init {
        setButtonStyle(ButtonTypes.FLAT)
    }

    /**
     * Returns the MaterialButton instance used in this custom button.
     *
     * @return The MaterialButton instance.
     */
    fun getButton(): MaterialButton = binding.customButton

    /**
     * Sets the text of the button.
     *
     * @param newText The new text to be displayed on the button.
     */
    fun setText(newText: String) {
        getButton().text = newText
    }

    /**
     * Hides the icon of the button.
     */
    fun hideIcon() {
        getButton().icon = null
        getButton().iconPadding = 0
    }

    /**
     * Sets a click listener for the button.
     *
     * @param callback The function to be called when the button is clicked.
     */
    fun onButtonClick(callback: () -> Unit) {
        if (isEnabled) callback()
    }

    /**
     * Sets a leading icon for the button.
     *
     * @param iconResId The resource ID of the icon to be set. If -1, a default icon is used.
     */
    fun setLeadingIcon(iconResId: Int = -1) {
        setIcon(iconResId, MaterialButton.ICON_GRAVITY_START)
    }

    /**
     * Sets a trailing icon for the button.
     *
     * @param iconResId The resource ID of the icon to be set. If -1, a default icon is used.
     */
    fun setTrailingIcon(iconResId: Int = -1) {
        setIcon(iconResId, MaterialButton.ICON_GRAVITY_END)
    }

    /**
     * Disables the button and applies the disabled style.
     */
    fun disableButton() {
        getButton().isEnabled = false
        val disabledColor = ContextCompat.getColor(context, R.color.custom_button_color_disabled)
        isEnabled = false
        applyButtonStyle(currentButtonType, disabledColor)
    }

    /**
     * Sets the button style to flat and updates the text.
     *
     * @param text The text to be displayed on the button.
     */
    fun setFlatButton(text: String) {
        setText(text)
        setButtonStyle(ButtonTypes.FLAT)
    }

    /**
     * Sets the button style to outlined and updates the text.
     *
     * @param text The text to be displayed on the button.
     */
    fun setOutlinedButton(text: String) {
        setText(text)
        setButtonStyle(ButtonTypes.OUTLINED)
    }

    /**
     * Sets the button style to transparent and updates the text.
     *
     * @param text The text to be displayed on the button.
     */
    fun setTransparentButton(text: String) {
        setText(text)
        setButtonStyle(ButtonTypes.TRANSPARENT)
    }

    /**
     * Returns the current button type.
     *
     * @return The current ButtonTypes value.
     */
    fun getCurrentButtonType(): ButtonTypes = currentButtonType

    /**
     * Sets the button style and applies the appropriate color.
     *
     * @param buttonType The ButtonTypes value to set.
     */
    private fun setButtonStyle(buttonType: ButtonTypes) {
        currentButtonType = buttonType
        val color = ContextCompat.getColor(context, R.color.custom_button_color_primary)
        applyButtonStyle(buttonType, color)
    }

    /**
     * Applies the button style based on the button type and color.
     *
     * @param buttonType The ButtonTypes value to apply.
     * @param color The color to use for the button style.
     */
    private fun applyButtonStyle(buttonType: ButtonTypes, color: Int) {
        when (buttonType) {
            ButtonTypes.FLAT -> applyFlatStyle(color)
            ButtonTypes.OUTLINED -> applyOutlinedStyle(color)
            ButtonTypes.TRANSPARENT -> applyTransparentStyle(color)
        }
    }

    /**
     * Applies the flat style to the button.
     *
     * @param color The color to use for the button background.
     */
    private fun applyFlatStyle(color: Int) {
        getButton().apply {
            if (isEnabled) {
                setTextColor(ContextCompat.getColor(context, R.color.custom_button_white))
                backgroundTintList = ColorStateList.valueOf(color)
                iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.custom_button_white))
            } else {
                setTextColor(ContextCompat.getColor(context, R.color.custom_button_disabled_text_color))
                backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.custom_button_disabled_background_color))
                iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.custom_button_disabled_icon_color))
            }
            strokeWidth = 0
        }
    }

    /**
     * Applies the outlined style to the button.
     *
     * @param color The color to use for the button outline and text.
     */
    private fun applyOutlinedStyle(color: Int) {
        val cornerRadius = context.resources.getDimension(R.dimen.custom_button_corner_radius)
        getButton().apply {
            backgroundTintList = null
            strokeColor = null
            strokeWidth = 0

            if (isEnabled) {
                setTextColor(color)
                iconTint = ColorStateList.valueOf(color)
                background = createOutlinedDrawable(context, color, cornerRadius, 1)
            } else {
                setTextColor(ContextCompat.getColor(context, R.color.custom_button_disabled_text_color))
                iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.custom_button_disabled_icon_color))
                background = createOutlinedDrawable(context, ContextCompat.getColor(context, R.color.custom_button_disabled_icon_color), cornerRadius, 1)
            }
        }
    }

    /**
     * Applies the transparent style to the button.
     *
     * @param color The color to use for the button text and icon.
     */
    private fun applyTransparentStyle(color: Int) {
        val cornerRadius = context.resources.getDimension(R.dimen.custom_button_transparent_corner_radius)
        getButton().apply {
            if (isEnabled) {
                setTextColor(color)
                iconTint = ColorStateList.valueOf(color)
            } else {
                setTextColor(ContextCompat.getColor(context, R.color.custom_button_disabled_text_color))
                iconTint = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.custom_button_disabled_icon_color))
            }
            setBackgroundColor(Color.TRANSPARENT)
            background = createOutlinedDrawable(context, Color.TRANSPARENT, cornerRadius, 0)
        }
    }

    /**
     * Sets the icon for the button.
     *
     * @param iconResId The resource ID of the icon to set.
     * @param gravity The gravity to apply to the icon.
     */
    private fun setIcon(iconResId: Int, gravity: Int) {
        val icon = if (iconResId != -1) {
            ContextCompat.getDrawable(context, iconResId)
        } else {
            ContextCompat.getDrawable(context, R.drawable.add_line)
        }
        getButton().icon = icon
        getButton().iconGravity = gravity
    }

    /**
     * Creates an outlined drawable for the button.
     *
     * @param context The context to use for resource resolution.
     * @param strokeColor The color of the stroke.
     * @param cornerRadius The radius of the corners.
     * @param strokeWidth The width of the stroke.
     * @return A Drawable representing the outlined button background.
     */
    private fun createOutlinedDrawable(
        context: Context, strokeColor: Int, cornerRadius: Float, strokeWidth: Int
    ): Drawable {
        val shapeDrawable = createShapeDrawable(strokeColor, cornerRadius, strokeWidth)
        val maskDrawable = createMaskDrawable(cornerRadius)
        val rippleColor = getRippleColor(context)

        return RippleDrawable(ColorStateList.valueOf(rippleColor), shapeDrawable, maskDrawable)
    }

    /**
     * Creates a shape drawable for the button background.
     *
     * @param strokeColor The color of the stroke.
     * @param cornerRadius The radius of the corners.
     * @param strokeWidth The width of the stroke.
     * @return A GradientDrawable representing the button shape.
     */
    private fun createShapeDrawable(strokeColor: Int, cornerRadius: Float, strokeWidth: Int): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setStroke(strokeWidth.dpToPx(context), strokeColor)
            setColor(Color.TRANSPARENT)
            this.cornerRadius = cornerRadius
        }
    }

    /**
     * Creates a mask drawable for the button ripple effect.
     *
     * @param cornerRadius The radius of the corners.
     * @return A GradientDrawable representing the button mask.
     */
    private fun createMaskDrawable(cornerRadius: Float): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.WHITE)
            this.cornerRadius = cornerRadius
        }
    }

    /**
     * Gets the ripple color for the button.
     *
     * @param context The context to use for theme attribute resolution.
     * @return The color to use for the ripple effect.
     */
    private fun getRippleColor(context: Context): Int {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.colorControlHighlight, typedValue, true)
        return typedValue.data
    }

    /**
     * Converts dp to pixels.
     *
     * @param context The context to use for density resolution.
     * @return The value in pixels.
     */
    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }
}