package com.example.project.DataBase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project.DataBase.model.EventCalendar

@Database(entities = [EventCalendar::class],version = 1,exportSchema = false)
abstract class EventCalendarDatabase:RoomDatabase() {

    abstract fun eventCalendarDao():EventCalendarDao

    companion object{

        @Volatile
        private var INSTANCE:EventCalendarDatabase? = null

        fun getDatabase(context: Context):EventCalendarDatabase{
            val tempInstance = EventCalendarDatabase.INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        EventCalendarDatabase::class.java,
                        "event_calendar_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}