package com.example.project.DataBase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.project.DataBase.model.Lesson
import com.example.project.DataBase.model.Subject

@Dao
interface LessonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLesson(lesson: Lesson)

    @Update
    suspend fun updateLesson(lesson: Lesson)

    @Delete
    suspend fun deleteLesson(lesson: Lesson)

    @Query("DELETE FROM lesson_table")
    suspend fun deleteAllLesson()

    @Query("SELECT * FROM lesson_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Lesson>>
}