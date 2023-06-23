package com.mehedisoftdev.sms.repository

import androidx.lifecycle.LiveData
import com.mehedisoftdev.sms.db.StudentDatabase
import com.mehedisoftdev.sms.models.Student

class StudentRepo(private val studentDatabase: StudentDatabase) {

    lateinit var studentsLiveData: LiveData<List<Student>>

    suspend fun getStudents(){
        studentsLiveData = studentDatabase.getStudentDao().getStudents()
    }

    suspend fun insertStudent(student: Student) {
      studentDatabase.getStudentDao().insertStudent(student)
    }

    suspend fun deleteStudent(student: Student) {
        studentDatabase.getStudentDao().deleteStudent(student)
    }

    suspend fun updateStudent(student: Student) {
        studentDatabase.getStudentDao().updateStudent(student)
    }

}