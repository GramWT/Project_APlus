package com.example.project.DataBase.repository

import androidx.lifecycle.LiveData
import com.example.project.DataBase.data.EventDao
import com.example.project.DataBase.model.Event

class EventRepository(private val eventDao: EventDao) {

    val readAllData: LiveData<List<Event>> = eventDao.readAllData()

    suspend fun addEvent(event: Event){
        eventDao.addEvent(event)
    }

    suspend fun updateEvent(event: Event){
        eventDao.updateEvent(event)
    }

    suspend fun deleteEvent(event: Event){
        eventDao.deleteEvent(event)
    }

    suspend fun deleteAllEvent(){
        eventDao.deleteAllEvent()
    }
}