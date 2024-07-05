package com.example.mworkordercomponentlibrary.components.listcardcomponent

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.mworkordercomponentlibrary.R
import com.example.mworkordercomponentlibrary.databinding.ListcardIssueCardBinding

class IssueCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val inflater = LayoutInflater.from(context)
    val binding: ListcardIssueCardBinding = ListcardIssueCardBinding.inflate(inflater, this, true)

    /**
     * Sets the height and width of the card.
     *
     * @param heightDp The desired height in dp. If -1, the height remains unchanged.
     * @param widthDp The desired width in dp. If -1, the width remains unchanged.
     */
    fun setHeightWidth(heightDp: Int = -1, widthDp: Int = -1) {
        if (heightDp != -1) {
            binding.cardList.layoutParams.height = dpToPx(heightDp)
        }
        if (widthDp != -1) {
            binding.cardList.layoutParams.width = dpToPx(widthDp)
        }
    }

    /**
     * Sets the issue ID text.
     *
     * @param newIssueId The new issue ID to be displayed.
     */
    fun setIssueId(newIssueId: String) {
        binding.issueId.text = newIssueId
    }

    /**
     * Sets the breakdown information.
     *
     * @param newIcon The drawable resource ID for the breakdown icon. If -1, the icon remains unchanged.
     * @param text The text to be displayed for the breakdown.
     */
    fun setBreakdown(@DrawableRes newIcon: Int = -1, text: String) {
        binding.breakdownLogo.visibility = View.VISIBLE
        if (newIcon != -1) {
            binding.breakdownLogo.setBackgroundResource(newIcon)
        }
        binding.breakdownText.text = text
    }

    /**
     * Sets the priority text.
     *
     * @param newPriorityText The new priority text to be displayed.
     */
    fun setPriorityText(newPriorityText: String) {
        binding.priorityText.text = newPriorityText
    }

    /**
     * Sets the issue title.
     *
     * @param newTitle The new title to be displayed.
     */
    fun setIssueTitle(newTitle: String) {
        binding.titleDesc.text = newTitle
    }

    /**
     * Sets the FL (First Label) information.
     *
     * @param flIcon The drawable resource ID for the FL icon. If -1, the icon remains unchanged.
     * @param flText The text to be displayed for the FL.
     */
    fun setFlLabel(@DrawableRes flIcon: Int = -1, flText: String) {
        if (flIcon != -1) {
            binding.elFqInfo.elInfo.icon.setBackgroundResource(flIcon)
        }
        binding.elFqInfo.elInfo.textField.text = flText
    }

    /**
     * Sets the EQ (Equipment) information.
     *
     * @param eqIcon The drawable resource ID for the EQ icon. If -1, the icon remains unchanged.
     * @param eqText The text to be displayed for the EQ.
     */
    fun setEqLabel(@DrawableRes eqIcon: Int = -1, eqText: String) {
        if (eqIcon != -1) {
            binding.elFqInfo.fqInfo.icon.setBackgroundResource(eqIcon)
        }
        binding.elFqInfo.fqInfo.textField.text = eqText
    }

    /**
     * Sets the WC (Work Center) label information.
     *
     * @param wcIcon The drawable resource ID for the WC icon. If -1, the icon remains unchanged.
     * @param wcLabelTest The text to be displayed for the WC label.
     */
    fun setWcLabel(@DrawableRes wcIcon: Int = -1, wcLabelTest: String) {
        binding.group2.wcLabel.visibility = View.VISIBLE
        if (wcIcon != -1) {
            binding.group2.wcLabelLayout.icon.setBackgroundResource(wcIcon)
        }
        binding.group2.wcLabelLayout.textField.text = wcLabelTest
    }

    /**
     * Sets the issue status information.
     *
     * @param statusIcon The drawable resource ID for the status icon. If -1, the icon remains unchanged.
     * @param status The text to be displayed for the status.
     */
    fun setIssueStatus(@DrawableRes statusIcon: Int = -1, status: String) {
        binding.group2.issueStatus.iconText.visibility = View.VISIBLE
        if (statusIcon != -1) binding.group2.issueStatus.icon.setBackgroundResource(statusIcon)
        binding.group2.issueStatus.textField.text = status
    }

    /**
     * Sets the chat media information.
     *
     * @param imageToShow The URL of the image to be displayed.
     * @param remainingImageCount The number of additional images. If greater than 0, it will be displayed.
     */
    fun setChatMedia(imageToShow: String, remainingImageCount: Int = -1) {
        if (imageToShow.isNotEmpty()) {
            loadImage(binding.issueImage, imageToShow)

            if (remainingImageCount > 0) {
                binding.imageQtText.text = "+$remainingImageCount"
                binding.imageQtText.visibility = View.VISIBLE
            } else {
                binding.imageQtText.visibility = View.GONE
            }
        } else {
            binding.issueImage.setImageResource(R.drawable.error_image)
            binding.imageQtText.visibility = View.GONE
        }
    }

    /**
     * Sets the assigned to information.
     *
     * @param imageToShow The URL of the assignee's image.
     * @param nameToShow The name of the assignee.
     * @param remainingPeopleCount The number of additional people assigned.
     */
    fun setAssignedToParam(imageToShow: String = "", nameToShow: String = "", remainingPeopleCount: Int) {
        if (nameToShow.isEmpty()) {
            binding.group2.assignedToBox.visibility = View.GONE
            return
        }

        binding.group2.assignedToBox.visibility = View.VISIBLE

        if (imageToShow.isNotEmpty()) {
            loadImage(binding.group2.secondImage, imageToShow)
        } else {
            loadImage(binding.group2.secondImage, R.drawable.error_image.toString())
        }

        val assignedText = if (remainingPeopleCount > 0) {
            "Assigned to $nameToShow +${remainingPeopleCount}others"
        } else {
            binding.group2.firstImage.visibility = View.GONE
            "Assigned to $nameToShow"
        }

        binding.group2.assignedToText.text = assignedText
    }

    /**
     * Sets the workorder ID in the operation header.
     *
     * @param newWorkorderId The new workorder ID to be displayed.
     */
    fun setWorkorderIdInOperation(newWorkorderId: String) {
        val headerView = binding.linearLayout.getChildAt(0)
        headerView.findViewById<TextView>(R.id.workorder_id).text = newWorkorderId
    }

    /**
     * Sets the operation ID in the operation header.
     *
     * @param newOperationId The new operation ID to be displayed.
     */
    fun setOperationId(newOperationId: String) {
        val headerView = binding.linearLayout.getChildAt(0)
        headerView.findViewById<TextView>(R.id.operation_id).text = newOperationId
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
     * Sets a click listener for the assigned to text.
     *
     * @param callback The function to be called when the assigned to text is clicked.
     */
    fun AssignedToClicked(callback: () -> Unit) {
        binding.group2.assignedToText.setOnClickListener {
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

    private fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    /**
     * Configures the layout for Arabic language support.
     */
    fun arabic() {
        this.layoutDirection = View.LAYOUT_DIRECTION_RTL
        binding.mainLayout.layoutDirection = View.LAYOUT_DIRECTION_RTL
        binding.mainLayout.textDirection = View.TEXT_DIRECTION_RTL
        requestLayout()
        invalidate()
    }

    /**
     * Configures the card for work order display.
     */
    fun workOrderCard() {
        binding.group2.assignedToBox.visibility = View.VISIBLE
        binding.titleDesc.text = "The work order title goes here"
        binding.issueId.text = "WO 182234"
    }

    /**
     * Configures the card for operation display.
     */
    fun operationCard() {
        val headerView = inflater.inflate(R.layout.listcard_operations_header, binding.root, false)
        binding.titleDesc.text = "The Operation title goes here"
        binding.linearLayout.removeAllViews()
        binding.linearLayout.addView(headerView, 0)
        binding.group2.wcLabel.visibility = View.VISIBLE
        binding.group2.assignedToBox.visibility = View.VISIBLE
        binding.titleDesc.text = "The work order title goes here"
        binding.issueId.text = "WO 182234"
        binding.chatImage.visibility = View.GONE
    }

    /**
     * Hides both EL and FQ information.
     */
    fun hideElFqInfo() {
        hideElInfo()
        hideFqInfo()
    }

    /**
     * Hides EL (Equipment Location) information.
     */
    fun hideElInfo() {
        binding.elFqInfo.elInfo.iconText.visibility = View.GONE
    }

    /**
     * Hides FQ (Frequency) information.
     */
    fun hideFqInfo() {
        binding.elFqInfo.fqInfo.iconText.visibility = View.GONE
    }

    /**
     * Hides the assigned to text.
     */
    fun hideIssueAssignedToText() {
        binding.group2.assignedToBox.visibility = View.GONE
    }

    /**
     * Hides the WC (Work Center) label.
     */
    fun hideWcLabel() {
        binding.group2.wcLabel.visibility = View.GONE
    }

    /**
     * Hides the issue status.
     */
    fun hideIssueStatus() {
        binding.group2.issueStatus.iconText.visibility = View.GONE
    }

    /**
     * Hides the chat media.
     */
    fun hideChatMedia() {
        binding.chatImage.visibility = View.GONE
    }

    /**
     * Configures the card for history header display.
     */
    fun historyHeader() {
        val color = ContextCompat.getColor(context, R.color.listcard_icon_tint)
        binding.breakdownLogo.backgroundTintList = ColorStateList.valueOf(color)
        binding.breakdownText.setTextColor(color)
        binding.seperator.setTextColor(color)
        binding.priorityText.setTextColor(color)
    }
}