package com.mehedisoftdev.sms.views

import android.app.Application
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mehedisoftdev.sms.R
import com.mehedisoftdev.sms.SMSApplication
import com.mehedisoftdev.sms.databinding.FragmentAddStudentBinding
import com.mehedisoftdev.sms.models.Student
import com.mehedisoftdev.sms.utils.Constants
import com.mehedisoftdev.sms.viewmodels.StudentViewModel
import com.mehedisoftdev.sms.viewmodels.StudentViewModelFactory
import java.util.Calendar


class AddStudentFragment : Fragment() {
    private var _binding: FragmentAddStudentBinding? = null
    private val binding get() = _binding!!
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddStudentBinding.inflate(layoutInflater, container, false)

        val repo = (requireActivity().application as SMSApplication).studentRepo
        studentViewModel = ViewModelProvider(requireActivity(),
            StudentViewModelFactory(repo)
            )[StudentViewModel::class.java]

        calendar = Calendar.getInstance()
        binding.admissionDate.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireActivity(),
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    // Update the selected endDate
                    val selectedDate = Calendar.getInstance()
                    selectedDate.set(selectedYear, selectedMonth, selectedDay)
                    val formattedDate = Constants.formatDate(selectedDate.time)
                    binding.admissionDate.text = formattedDate
                }, year, month, day)

            datePickerDialog.show()
        }

        // action for store button (i.e store student)
        binding.btnStore.setOnClickListener {
            actionBtnStore()
        }
        return binding.root
    }

    private fun actionBtnStore() {

        setNullError() // clear error

        if(!validateForm()) {
            return
        }

        // store to database
        val student = Student(
            0,
            binding.studentName.text.toString().trim(),
            binding.fatherName.text.toString().trim(),
            binding.admissionDate.text.toString().trim(),
            binding.tuitionFees.text.toString().trim().toDouble(),
            binding.contactNumber.text.toString().trim(),
        )
        studentViewModel.insertStudent(student)
        Toast.makeText(requireContext(), "Student added", Toast.LENGTH_SHORT)
            .show()
        findNavController().navigate(R.id.action_addStudentFragment_to_dashboardFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun validateForm() : Boolean {
        if(binding.studentName.text.toString().trim().isEmpty()) {
            binding.studentName.error = "Field is required"
            return false
        }
        else if(binding.admissionDate.text.toString().trim().isEmpty()) {
            binding.admissionDate.error = "Field is required"
            return false
        }
        else if(binding.tuitionFees.text.toString().trim().isEmpty()) {
            binding.tuitionFees.error = "Field is required"
            return false
        }

        return true
    }

    private fun setNullError() {
        binding.studentName.error = null
        binding.tuitionFees.error = null
        binding.admissionDate.error = null
    }
}