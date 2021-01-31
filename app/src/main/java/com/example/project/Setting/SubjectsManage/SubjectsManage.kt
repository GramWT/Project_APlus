package com.example.project.Setting.SubjectsManage

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.MainActivity
import com.example.project.Notifications.Exam.Mid.MidExamAdapter
import com.example.project.R
import com.example.project.databinding.FragmentSubjectsManageBinding
import kotlinx.android.synthetic.main.fragment_setting.view.*
import kotlinx.android.synthetic.main.fragment_subjects_manage.view.*


class SubjectsManage : Fragment() {

    private lateinit var binding:FragmentSubjectsManageBinding
    private lateinit var mSubjectModel: SubjectViewModel
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onResume() {
        super.onResume()
        val a = activity as MainActivity
        a.hideBottomNav()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentSubjectsManageBinding.inflate(layoutInflater)

        var adapter = SubjectsManageAdapter()
        binding.subjectsManageRecycleView.adapter = adapter

        gridLayoutManager = GridLayoutManager(requireContext(),1,LinearLayoutManager.VERTICAL,false)

        binding.subjectsManageRecycleView.layoutManager = gridLayoutManager


        binding.backButtonSjm.setOnClickListener {
            val action = SubjectsManageDirections.actionSubjectsManageNavToNavSetting()
            findNavController().navigate(action)
            val a = activity as MainActivity
            a.showBottomNav()
        }

        View.OnClickListener {

        }

        binding.deleteAllButtonSjm.setOnClickListener {
            deleteAllSubjects()
        }

        binding.addSubjectButtonSjm.setOnClickListener {
            val action = SubjectsManageDirections.actionSubjectsManageNavToSubjectsManageAdd()
            findNavController().navigate(action)
        }

        mSubjectModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mSubjectModel.readAllData.observe(viewLifecycleOwner,{subject ->
            adapter.setData(subject)
        })

        return binding.root

    }

    private fun deleteAllSubjects(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){ _, _ ->
            mSubjectModel.deleteAllSubject()
            Toast.makeText(
                    requireContext(),
                    "Successfully Removed All Subjects",
                    Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No"){ _, _ ->}
        builder.setTitle("Delete All Subjects ?")
        builder.setMessage("Are you sure you want to delete all subject?")
        builder.create().show()

    }


}