package com.example.project.Setting.SubjectsManage

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.MainActivity
import com.example.project.R
import com.example.project.databinding.FragmentSubjectsManageBinding
import kotlinx.android.synthetic.main.activity_main.*


class SubjectsManage : Fragment() {

    private lateinit var binding: FragmentSubjectsManageBinding
    private lateinit var mSubjectModel: SubjectViewModel
    private var gridLayoutManager: GridLayoutManager? = null
    private lateinit var mEventCalendar: EventCalendarViewModel
    private lateinit var mSubject: List<Subject>
    private lateinit var mAlarmService: AlarmService


    override fun onResume() {
        super.onResume()
        val a = activity as MainActivity
        a.bottom_navigation.visibility = View.GONE
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentSubjectsManageBinding.inflate(layoutInflater)

        var adapter = SubjectsManageAdapter()
        binding.subjectsManageRecycleView.adapter = adapter
        mAlarmService = AlarmService(requireContext())

        gridLayoutManager = GridLayoutManager(requireContext(), 1, LinearLayoutManager.VERTICAL, false)

        mEventCalendar = ViewModelProvider(this).get(EventCalendarViewModel::class.java)

        binding.subjectsManageRecycleView.layoutManager = gridLayoutManager


        binding.backButtonSjm.setOnClickListener {
            val a = activity as MainActivity
            a.bottom_navigation.visibility = View.VISIBLE

            if (a.bottom_navigation.selectedItemId == R.id.menu_exam) {
                a.bottom_navigation.selectedItemId = R.id.menu_setting
            } else if (a.bottom_navigation.selectedItemId == R.id.menu_setting) {
                val action = SubjectsManageDirections.actionSubjectsManageNavToNavSetting()
                findNavController().navigate(action)
            }
        }



        binding.deleteAllButtonSjm.setOnClickListener {
            deleteAllSubjects()
            mEventCalendar.deleteByType(1)

            mSubjectModel.readAllData.observe(viewLifecycleOwner, { subject ->
                mSubject = subject
                for (i in mSubject.indices) {

                    val idMid: Int = "1${mSubject[i].id}".toInt()
                    val idFinal: Int = "2${mSubject[i].id}".toInt()

                    println("List Id : ${mSubject[i].id}")

                    mAlarmService.cancelExamAlarm(idMid)
                    mAlarmService.cancelExamAlarm(idFinal)
                }

            })
        }

        binding.addSubjectButtonSjm.setOnClickListener {
            val action = SubjectsManageDirections.actionSubjectsManageNavToSubjectsManageAdd()
            findNavController().navigate(action)
        }

        mSubjectModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mSubjectModel.readAllData.observe(viewLifecycleOwner, { subject ->
            adapter.setData(subject)
        })


        binding.pdfImportButtonSjm.setOnClickListener {
            mSubjectModel.deleteById(111111)
        }

        return binding.root

    }

    private fun deleteAllSubjects() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            mSubjectModel.deleteAllSubject()
            Toast.makeText(
                    requireContext(),
                    "Successfully Removed All Subjects",
                    Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete All Subjects ?")
        builder.setMessage("Are you sure you want to delete all subject?")
        builder.create().show()

    }
}