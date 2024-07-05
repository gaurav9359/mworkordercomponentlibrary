package com.example.buttonstesing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.componentlibrarydemo.R
import com.example.componentlibrarydemo.databinding.FragmentListCardBinding

class ListCardFragment : Fragment() {

    private var _binding: FragmentListCardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupIssueCard()
        setupOperationCard()
        setupWorkOrderCard()
        setupArabicCards()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupIssueCard() {
        val issueCard = binding.issueCard
        issueCard.onImageClicked { sendToast("Image clicked") }
    }

    private fun setupOperationCard() {
        val operationCard = binding.operationsCard
        operationCard.operationCard()
        operationCard.onImageClicked { sendToast("Image clicked") }
        operationCard.AssignedToClicked { sendToast("Assigned to clicked") }
        operationCard.setWcLabel(R.drawable.add_line, "oreno")
    }

    private fun setupWorkOrderCard() {
        val woCard = binding.workOrderCard
        woCard.workOrderCard()
        woCard.onImageClicked { sendToast("Image clicked") }
        woCard.AssignedToClicked { sendToast("Assigned to clicked") }
    }

    private fun setupArabicCards() {
        val issueCardArabic = binding.issueCardArabic
        issueCardArabic.arabic()
        issueCardArabic.onImageClicked { sendToast("Image clicked") }

        val operationCardArabic = binding.operationsCardArabic
        operationCardArabic.operationCard()
        operationCardArabic.arabic()
        operationCardArabic.onImageClicked { sendToast("Image clicked") }
        operationCardArabic.AssignedToClicked { sendToast("Assigned to clicked") }

        val woCardArabic = binding.workOrderCardArabic
        woCardArabic.workOrderCard()
        woCardArabic.arabic()
        woCardArabic.onImageClicked { sendToast("Image clicked") }
        woCardArabic.AssignedToClicked { sendToast("Assigned to clicked") }
    }

    private fun sendToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
