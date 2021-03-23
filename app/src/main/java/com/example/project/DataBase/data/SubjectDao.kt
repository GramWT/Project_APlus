package com.example.project.DataBase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.project.DataBase.model.Subject

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubject(subject: Subject)

    @Update
    suspend fun updateSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query("DELETE FROM subject_table")
    suspend fun deleteAllSubjects()

    @Query("SELECT * FROM subject_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Subject>>

    @Query("DELETE FROM subject_table WHERE id = :id")
    suspend fun deleteById(id:Int)
}