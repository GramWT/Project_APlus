package com.example.project.DataBase.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.project.DataBase.data.SubjectDatabase
import com.example.project.DataBase.data.SubjectUploadDatabase
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.model.SubjectUpload
import com.example.project.DataBase.repository.SubjectRepository
import com.example.project.DataBase.repository.SubjectUploadRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectUploadViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<SubjectUpload>>
    private val repository: SubjectUploadRepository

    init {
        val subjectDao = SubjectUploadDatabase.getDatabase(application).subjectUpload()
        repository = SubjectUploadRepository(subjectDao)
        readAllData = repository.readAllData
    }

    fun addSubject(subject:SubjectUpload){
        viewModelScope.launch(Dispatchers.IO){
            repository.addSubject(subject)
        }
    }

    fun updateSubject(subject: SubjectUpload){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateSubject(subject)
        }
    }

    fun deleteSubject(subject: SubjectUpload){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteSubject(subject)
        }
    }

    fun deleteAllSubject(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllSubjects()
        }
    }

    fun deleteById(id:Int){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteById(id)
        }
    }


}