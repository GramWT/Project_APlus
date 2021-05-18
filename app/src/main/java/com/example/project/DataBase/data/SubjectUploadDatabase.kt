package com.example.project.DataBase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project.DataBase.model.SubjectUpload

@Database(entities = [SubjectUpload::class],version = 1,exportSchema = false)
abstract class SubjectUploadDatabase:RoomDatabase() {

    abstract fun subjectUpload():SubjectUploadDao

    companion object{
        @Volatile
        private var INSTANCE:SubjectUploadDatabase? = null

        fun getDatabase(context: Context):SubjectUploadDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubjectUploadDatabase::class.java,
                    "subject_upload_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}