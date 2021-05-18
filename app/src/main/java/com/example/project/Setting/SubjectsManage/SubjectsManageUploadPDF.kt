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

        binding = FragmentSubjectsManageUploadPDBinding.inflate(layoutInflater)

        var adapter = SubjectUploadAdapter()
        binding.uploadRecycle.adapter = adapter
        mAlarmService = AlarmService(requireContext())
        mEventCalendar = ViewModelProvider(this).get(EventCalendarViewModel::class.java)
        gridLayoutManager =
            GridLayoutManager(requireContext(), 1, LinearLayoutManager.VERTICAL, false)
        binding.uploadRecycle.layoutManager = gridLayoutManager

        binding.uploadButton.setOnClickListener {
            val subject = SubjectUpload(
                111111,
                "1111111",
                "TEST",
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

            mSubjectModel.addSubject(subject)
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

}