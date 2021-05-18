package com.example.project.Setting.SubjectsManage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.model.SubjectUpload
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.SubjectUploadViewModel
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.R
import com.example.project.databinding.FragmentSubjectsManageBinding
import com.example.project.databinding.FragmentSubjectsManageUploadPDBinding

class SubjectsManageUploadPDF : Fragment() {

    private lateinit var binding: FragmentSubjectsManageUploadPDBinding
    private lateinit var mSubjectModel: SubjectUploadViewModel
    private var gridLayoutManager: GridLayoutManager? = null
    private lateinit var mEventCalendar: EventCalendarViewModel
    private lateinit var mSubjectUpload: List<SubjectUpload>
    private lateinit var mAlarmService: AlarmService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        data class Subject(val sid:String,val name:String)

        val dataModel:List<Subject> = arrayListOf(Subject("080101231","ENGLISH II"),Subject("040130761","HUMAN AND BODY"),Subject("010303502","PHYSICS FOR LIFE"))
        binding = FragmentSubjectsManageUploadPDBinding.inflate(layoutInflater)

        val adapter = SubjectUploadAdapter()
        binding.uploadRecycle.adapter = adapter
        mAlarmService = AlarmService(requireContext())
        mEventCalendar = ViewModelProvider(this).get(EventCalendarViewModel::class.java)
        gridLayoutManager =
            GridLayoutManager(requireContext(), 1, LinearLayoutManager.VERTICAL, false)
        binding.uploadRecycle.layoutManager = gridLayoutManager

        binding.uploadButton.setOnClickListener {
            mSubjectModel.deleteAllSubject()
            dataModel.forEachIndexed { index, subject ->
                val subjects = SubjectUpload(
                    randomId(),
                    subject.sid,
                    subject.name,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
                )

                mSubjectModel.addSubject(subjects)
            }
        }

        binding.backButton.setOnClickListener {
            val action = SubjectsManageUploadPDFDirections.actionSubjectsManageUploadPDFToSubjectsManageNav()
            findNavController().navigate(action)
        }

        mSubjectModel = ViewModelProvider(this).get(SubjectUploadViewModel::class.java)
        mSubjectModel.readAllData.observe(viewLifecycleOwner, { subject ->
            adapter.setData(subject)
        })

        return binding.root
    }

    private fun randomId(): Int {
        var randomId =
            "3${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}".toInt()
        val list = arrayListOf<Int>()
        var checked = false

        mSubjectModel.readAllData.observe(viewLifecycleOwner, { subject ->
            mSubjectUpload = subject

            for (i in 0..mSubjectUpload.size - 1) {

                list.add(mSubjectUpload[i].id)
            }

            while (checked == false) {
                if (randomId in list) {
                    randomId =
                        "3${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}".toInt()
                    println(randomId)
                } else {
                    checked = true
                }
            }
        })
        return randomId
    }

}