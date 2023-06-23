package com.mehedisoftdev.sms.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedisoftdev.sms.models.Student
import com.mehedisoftdev.sms.repository.StudentRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(private val studentRepo: StudentRepo) : ViewModel() {


    val studentsLiveData get() = studentRepo.studentsLiveData

    init {
        getStudents()
    }

    fun getStudents()  {
        viewModelScope.launch {
           studentRepo.getStudents()
        }

    }

    fun insertStudent(student: Student) {
        viewModelScope.launch {
            studentRepo.insertStudent(student)
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            studentRepo.deleteStudent(student)
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            studentRepo.updateStudent(student)
        }
    }
}