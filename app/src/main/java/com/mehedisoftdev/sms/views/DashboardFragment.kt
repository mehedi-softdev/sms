package com.mehedisoftdev.sms.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mehedisoftdev.sms.R
import com.mehedisoftdev.sms.adapters.StudentListAdapter
import com.mehedisoftdev.sms.databinding.FragmentDashboardBinding
import com.mehedisoftdev.sms.models.Student

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var studentList: List<Student>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()

        // floating action button
        binding.btnAddStudent.setOnClickListener {
            // goto add student fragment
            findNavController().navigate(R.id.action_dashboardFragment_to_addStudentFragment)
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.studentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.studentRecyclerView.setHasFixedSize(true)
        val listAdapter : StudentListAdapter = StudentListAdapter()

        // student list
        studentList = listOf(Student(1))

        listAdapter.submitList(studentList)

        // click listener
        listAdapter.setOnItemClickListener(object: StudentListAdapter.OnItemClickListener {
            override fun onItemClick(student: Student) {
                val bundle = Bundle()
                bundle.putString("student", Gson().toJson(student))
                findNavController().navigate(R.id.action_dashboardFragment_to_updateStudentFragment, bundle)
            }

        })
        binding.studentRecyclerView.adapter = listAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}