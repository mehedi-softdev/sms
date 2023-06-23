package com.mehedisoftdev.sms.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mehedisoftdev.sms.models.Student

@Dao
interface StudentDao {
   @Query("SELECT * FROM students")
   fun getStudents() : LiveData<List<Student>>

   @Insert
   suspend fun insertStudent(student: Student)

   @Update
   suspend fun updateStudent(student: Student)

   @Delete
   suspend fun deleteStudent(student: Student)
}