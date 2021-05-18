package com.example.project.DataBase.repository

import androidx.lifecycle.LiveData
import com.example.project.DataBase.data.SubjectUploadDao
import com.example.project.DataBase.model.SubjectUpload

class SubjectUploadRepository(private val subjectUploadDao: SubjectUploadDao) {

    val readAllData: LiveData<List<SubjectUpload>> = subjectUploadDao.readAllData()

    suspend fun addSubject(subjectUpload: SubjectUpload) {
        subjectUploadDao.addSubject(subjectUpload)
    }

    suspend fun updateSubject(subjectUpload: SubjectUpload) {
        subjectUploadDao.updateSubject(subjectUpload)
    }

    suspend fun deleteSubject(subjectUpload: SubjectUpload) {
        subjectUploadDao.deleteSubject(subjectUpload)
    }

    suspend fun deleteAllSubjects() {
        subjectUploadDao.deleteAllSubjects()
    }

    suspend fun deleteById(id: Int) {
        subjectUploadDao.deleteById(id)
    }
}