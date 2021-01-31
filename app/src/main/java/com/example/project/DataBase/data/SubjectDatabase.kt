package com.example.project.DataBase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project.DataBase.model.Subject

@Database(entities = [Subject::class],version = 1,exportSchema = false)
abstract class SubjectDatabase:RoomDatabase() {

    abstract fun subjectDao():SubjectDao

    companion object{

        @Volatile
        private var INSTANCE:SubjectDatabase? = null

        fun getDatabase(context: Context):SubjectDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SubjectDatabase::class.java,
                    "subject_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}