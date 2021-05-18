package com.example.project.DataBase.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.model.SubjectUpload

@Dao
interface SubjectUploadDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubject(subject: SubjectUpload)

    @Update
    suspend fun updateSubject(subject: SubjectUpload)

    @Delete
    suspend fun deleteSubject(subject: SubjectUpload)

    @Query("DELETE FROM subject_upload_table")
    suspend fun deleteAllSubjects()

    @Query("SELECT * FROM subject_upload_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<SubjectUpload>>

    @Query("DELETE FROM subject_upload_table WHERE id = :id")
    suspend fun deleteById(id:Int)
}