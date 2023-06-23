package com.mehedisoftdev.sms.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mehedisoftdev.sms.repository.StudentRepo

class StudentViewModelFactory(private val studentRepo: StudentRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudentViewModel(studentRepo) as T
    }
}