package com.mehedisoftdev.sms.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.mehedisoftdev.sms.R
import com.mehedisoftdev.sms.databinding.FragmentAddStudentBinding
import com.mehedisoftdev.sms.databinding.FragmentUpdateStudentBinding
import com.mehedisoftdev.sms.models.Student


class UpdateStudentFragment : Fragment() {
    private var _binding: FragmentUpdateStudentBinding? = null
    private val binding get() = _binding!!
    private lateinit var student: Student

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateStudentBinding.inflate(layoutInflater, container, false)

        val studentString = arguments?.getString("student")
        if(studentString != null) {
            student = Gson().fromJson(studentString, Student::class.java)
            updateUi(student)
        }

        return binding.root
    }

    private fun updateUi(student: Student) {
        binding.studentId.text = student.sid.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}