package com.example.project.DataBase.repository

import androidx.lifecycle.LiveData
import com.example.project.DataBase.data.SubjectDao
import com.example.project.DataBase.model.Subject

class SubjectRepository(private val subjectDao: SubjectDao) {

    val readAllData: LiveData<List<Subject>> = subjectDao.readAllData()

    suspend fun addSubject(subject: Subject){
        subjectDao.addSubject(subject)
    }

    suspend fun updateSubject(subject: Subject){
        subjectDao.updateSubject(subject)
    }

    suspend fun deleteSubject(subject: Subject){
        subjectDao.deleteSubject(subject)
    }

    suspend fun deleteAllSubjects(){
        subjectDao.deleteAllSubjects()
    }

    suspend fun deleteById(id:Int){
        subjectDao.deleteById(id)
    }
}