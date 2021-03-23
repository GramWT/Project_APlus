package com.example.project.DataBase.repository

import androidx.lifecycle.LiveData
import com.example.project.DataBase.data.EventCalendarDao
import com.example.project.DataBase.model.EventCalendar

class EventCalendarRepository(private val eventCalendarDao: EventCalendarDao) {

    val readAllData: LiveData<List<EventCalendar>> = eventCalendarDao.readAllData()



    suspend fun addEventCalendar(event:EventCalendar){
        eventCalendarDao.addEventCalendar(event)
    }

    suspend fun updateEventCalendar(event:EventCalendar){
        eventCalendarDao.updateEventCalendar(event)
    }

    suspend fun deleteEventCalendar(event: EventCalendar){
        eventCalendarDao.deleteEventCalendar(event)
    }

    suspend fun deleteAllEventCalendar(){
        eventCalendarDao.deleteAllEventCalendar()
    }
    suspend fun deleteById(id:Int){
        eventCalendarDao.deleteById(id)
    }

    fun readDataById(id: Int):LiveData<List<EventCalendar>>{
        return eventCalendarDao.readDataById(id)
    }

    fun readDataByDate(day: Int,month: Int,year: Int):LiveData<List<EventCalendar>>{
        return eventCalendarDao.readDataByDate(day,month,year)
    }



}