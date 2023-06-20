package com.mehedisoftdev.sms.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mehedisoftdev.sms.R
import com.mehedisoftdev.sms.databinding.FragmentAddStudentBinding


class AddStudentFragment : Fragment() {
    private var _binding: FragmentAddStudentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddStudentBinding.inflate(layoutInflater, container, false)

        // action for store button (i.e store student)
        binding.btnStore.setOnClickListener {
            // store data to server

            findNavController().navigate(R.id.action_addStudentFragment_to_dashboardFragment)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}