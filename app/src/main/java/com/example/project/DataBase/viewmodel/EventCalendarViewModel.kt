package com.example.project.DataBase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.project.DataBase.data.EventCalendarDatabase
import com.example.project.DataBase.model.EventCalendar
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.repository.EventCalendarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventCalendarViewModel(application: Application):AndroidViewModel(application) {

    val readAllData: LiveData<List<EventCalendar>>

    private val repository:EventCalendarRepository

    init {
        val eventCalendarDao = EventCalendarDatabase.getDatabase(application).eventCalendarDao()
        repository = EventCalendarRepository(eventCalendarDao)
        readAllData = repository.readAllData
    }

    fun addEventCalendar(event:EventCalendar){
        viewModelScope.launch(Dispatchers.IO){
            repository.addEventCalendar(event)
        }
    }

    fun updateEventCalendar(event: EventCalendar){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateEventCalendar(event)
        }
    }

    fun deleteEventCalendar(event: EventCalendar){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteEventCalendar(event)
        }
    }

    fun deleteAllEventDatabase(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllEventCalendar()
        }
    }

    fun deleteById(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteById(id)
        }
    }

    fun deleteByType(type:Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteByType(type)
        }
    }

    fun readDataById(id: Int):LiveData<List<EventCalendar>>{
        return repository.readDataById(id)
    }

    fun readDataByDate(day: Int,month: Int,year: Int):LiveData<List<EventCalendar>>{
        return repository.readDataByDate(day,month,year)
    }



}