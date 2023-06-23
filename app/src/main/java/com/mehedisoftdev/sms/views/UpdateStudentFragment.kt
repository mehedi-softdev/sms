package com.mehedisoftdev.sms.views

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.google.gson.Gson
import com.mehedisoftdev.sms.R
import com.mehedisoftdev.sms.SMSApplication
import com.mehedisoftdev.sms.databinding.FragmentAddStudentBinding
import com.mehedisoftdev.sms.databinding.FragmentUpdateStudentBinding
import com.mehedisoftdev.sms.models.Student
import com.mehedisoftdev.sms.utils.Constants
import com.mehedisoftdev.sms.viewmodels.StudentViewModel
import com.mehedisoftdev.sms.viewmodels.StudentViewModelFactory
import java.util.Calendar


class UpdateStudentFragment : Fragment() {
    private var _binding: FragmentUpdateStudentBinding? = null
    private val binding get() = _binding!!
    private lateinit var student: Student
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateStudentBinding.inflate(layoutInflater, container, false)

        val repo = (requireActivity().application as SMSApplication).studentRepo
        studentViewModel = ViewModelProvider(requireActivity(),
        StudentViewModelFactory(repo))[StudentViewModel::class.java]

        val studentString = arguments?.getString("student")
        if(studentString != null) {
            student = Gson().fromJson(studentString, Student::class.java)
            updateUi(student)
        }

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

        binding.btnUpdate.setOnClickListener {
            doUpdate()
        }

        binding.btnDelete.setOnClickListener {
            doDelete()
        }

        return binding.root
    }

    private fun doUpdate() {
        setNullError() // clear error

        if(!validateForm()) {
            return
        }

        // store to database
        val updatedStudent = Student(
            student.sid,
            binding.studentName.text.toString().trim(),
            binding.fatherName.text.toString().trim(),
            binding.admissionDate.text.toString().trim(),
            binding.tuitionFees.text.toString().trim().toDouble(),
            binding.contactNumber.text.toString().trim(),
        )

        studentViewModel.updateStudent(updatedStudent)
        Toast.makeText(requireActivity(), "Student updated", Toast.LENGTH_SHORT)
            .show()
        findNavController().navigate(R.id.action_updateStudentFragment_to_dashboardFragment)
    }

    private fun doDelete() {
        // delete student
        studentViewModel.deleteStudent(student)
        Toast.makeText(requireActivity(), "Student deleted", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateStudentFragment_to_dashboardFragment)
    }

    private fun updateUi(student: Student) {
        binding.studentId.text = student.sid.toString()
        binding.studentName.setText(student.studentName)
        binding.fatherName.setText(student.fatherName)
        binding.admissionDate.text = student.admissionDate
        binding.tuitionFees.setText(student.tuitionFees.toString())
        binding.contactNumber.setText(student.contactNumber)
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