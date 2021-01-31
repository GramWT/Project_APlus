package com.example.project.DataBase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.project.DataBase.data.SubjectDatabase
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.repository.SubjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectViewModel(application: Application):AndroidViewModel(application) {

    val readAllData: LiveData<List<Subject>>
    private val repository:SubjectRepository

    init {
        val subjectDao = SubjectDatabase.getDatabase(application).subjectDao()
        repository = SubjectRepository(subjectDao)
        readAllData = repository.readAllData
    }

    fun addSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            repository.addSubject(subject)
        }
    }

    fun updateSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateSubject(subject)
        }
    }

    fun deleteSubject(subject: Subject){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteSubject(subject)
        }
    }

    fun deleteAllSubject(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllSubjects()
        }
    }
}