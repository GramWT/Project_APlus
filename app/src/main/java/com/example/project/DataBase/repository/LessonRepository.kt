package com.example.project.DataBase.repository

import androidx.lifecycle.LiveData
import com.example.project.DataBase.data.LessonDao
import com.example.project.DataBase.model.Lesson
import com.example.project.DataBase.model.Subject

class LessonRepository(private val lessonDao: LessonDao) {

    val readAllData: LiveData<List<Lesson>> = lessonDao.readAllData()

    suspend fun addLesson(lesson: Lesson){
        lessonDao.addLesson(lesson)
    }

    suspend fun updateLesson(lesson: Lesson){
        lessonDao.updateLesson(lesson)
    }

    suspend fun deleteLesson(lesson: Lesson){
        lessonDao.deleteLesson(lesson)
    }

    suspend fun deleteAllLessons(){
        lessonDao.deleteAllLesson()
    }
}