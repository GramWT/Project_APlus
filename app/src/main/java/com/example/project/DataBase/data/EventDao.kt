package com.example.project.DataBase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.project.DataBase.model.Event



@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEvent(event: Event)

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("DELETE FROM event_table")
    suspend fun deleteAllEvent()

    @Query("SELECT * FROM event_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Event>>
}