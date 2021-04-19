package com.example.project.Notifications.Exam.Final

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.Notifications.Exam.Mid.MidExamAdapter
import com.example.project.R
import com.example.project.databinding.FragmentFinalExamNotificationBinding

class FinalExamNotification : Fragment() {

    private lateinit var binding:FragmentFinalExamNotificationBinding
    private lateinit var mSubjectModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFinalExamNotificationBinding.inflate(layoutInflater)

        var at = activity as Context

        var adapter = FinalExamAdapter(at)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mSubjectModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mSubjectModel.readAllData.observe(viewLifecycleOwner,{subject ->
            adapter.setData(subject)
        })

        return binding.root
    }

}