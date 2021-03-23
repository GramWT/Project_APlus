package com.example.project.DataBase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.project.DataBase.data.EventDatabase
import com.example.project.DataBase.data.LessonDatabase
import com.example.project.DataBase.model.Event
import com.example.project.DataBase.model.Lesson
import com.example.project.DataBase.repository.EventRepository
import com.example.project.DataBase.repository.LessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LessonViewModel(application: Application):AndroidViewModel(application) {

    val readAllData: LiveData<List<Lesson>>
    private val repository: LessonRepository

    init {
        val lessonDao = LessonDatabase.getDatabase(application).lessonDao()
        repository = LessonRepository(lessonDao)
        readAllData = repository.readAllData
    }

    fun addLesson(lesson: Lesson){
        viewModelScope.launch(Dispatchers.IO){
            repository.addLesson(lesson)
        }
    }

    fun updateLesson(lesson: Lesson){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateLesson(lesson)
        }
    }

    fun deleteLesson(lesson: Lesson){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteLesson(lesson)
        }
    }

    fun deleteAllLesson(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllLessons()
        }
    }
}