package com.example.project.DataBase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.project.DataBase.model.EventCalendar


@Dao
interface EventCalendarDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEventCalendar(event: EventCalendar)

    @Update
    suspend fun updateEventCalendar(event: EventCalendar)

    @Delete
    suspend fun deleteEventCalendar(event: EventCalendar)

    @Query("DELETE FROM event_calendar_table")
    suspend fun deleteAllEventCalendar()

    @Query("SELECT * FROM event_calendar_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<EventCalendar>>


    @Query("DELETE FROM event_calendar_table WHERE id = :id")
    suspend fun deleteById(id:Int)

    @Query("DELETE FROM event_calendar_table WHERE type = :type")
    suspend fun deleteByType(type:Int)

    @Query("SELECT * FROM event_calendar_table Where type = :id")
    fun readDataById(id: Int): LiveData<List<EventCalendar>>

    @Query("SELECT * FROM event_calendar_table Where day = :day AND month = :month AND year = :year")
    fun readDataByDate(day: Int,month: Int,year: Int): LiveData<List<EventCalendar>>
}