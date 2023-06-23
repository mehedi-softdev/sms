package com.mehedisoftdev.sms

import android.app.Application
import com.mehedisoftdev.sms.db.StudentDatabase
import com.mehedisoftdev.sms.repository.StudentRepo

class SMSApplication: Application() {

    lateinit var studentRepo: StudentRepo

    override fun onCreate() {
        super.onCreate()
        val database = StudentDatabase.getInstance(applicationContext);
        studentRepo = StudentRepo(database)

    }
}