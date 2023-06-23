package com.mehedisoftdev.sms.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mehedisoftdev.sms.models.Student


@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase: RoomDatabase() {
    abstract fun getStudentDao() : StudentDao

    companion object {
        private var instance : StudentDatabase? = null
        fun getInstance(context: Context) : StudentDatabase {
            if(instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context,
                        StudentDatabase::class.java,
                        "sms.db"
                    ).build()
                }
            }
            return  instance!!
        }
    }
}