package com.mehedisoftdev.sms.views

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mehedisoftdev.sms.R
import com.mehedisoftdev.sms.SMSApplication
import com.mehedisoftdev.sms.adapters.StudentListAdapter
import com.mehedisoftdev.sms.databinding.FragmentDashboardBinding
import com.mehedisoftdev.sms.models.Student
import com.mehedisoftdev.sms.viewmodels.StudentViewModel
import com.mehedisoftdev.sms.viewmodels.StudentViewModelFactory

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private var totalAmount : Double = 0.toDouble()

    private lateinit var studentList: List<Student>
    private lateinit var studentViewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)

        val repo = (requireActivity().application as SMSApplication).studentRepo
        studentViewModel = ViewModelProvider(requireActivity(),
            StudentViewModelFactory(repo)
        )[StudentViewModel::class.java]

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
        studentViewModel.getStudents()
        studentViewModel.studentsLiveData.observe(requireActivity()) {
            studentList = it
            listAdapter.submitList(studentList)
            val activity: FragmentActivity? = activity

           if(activity != null && isAdded) { // fragment is attached or not
               binding.studentRecyclerView.adapter = listAdapter
               for(item in it) {
                   totalAmount += item.tuitionFees
               }
               binding.totalFees.text = String.format(getString(R.string.bdt), totalAmount)
           }
        }




        // click listener
        listAdapter.setOnItemClickListener(object: StudentListAdapter.OnItemClickListener {
            override fun onItemClick(student: Student) {
                val bundle = Bundle()
                bundle.putString("student", Gson().toJson(student))
                findNavController().navigate(R.id.action_dashboardFragment_to_updateStudentFragment, bundle)
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}