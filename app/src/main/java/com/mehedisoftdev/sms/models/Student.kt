package com.mehedisoftdev.sms.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val sid: Int,
    val studentName: String,
    val fatherName: String,
    val admissionDate: String,
    val tuitionFees: Double,
    val contactNumber: String
)
