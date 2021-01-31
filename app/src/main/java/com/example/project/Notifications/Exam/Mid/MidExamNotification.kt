package com.example.project.Notifications.Exam.Mid

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.databinding.FragmentMidExamNotificationBinding


class MidExamNotification : Fragment() {

    private lateinit var binding:FragmentMidExamNotificationBinding
    private lateinit var mSubjectModel:SubjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMidExamNotificationBinding.inflate(layoutInflater)

        var at = activity as Context

        var adapter = MidExamAdapter(at)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mSubjectModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mSubjectModel.readAllData.observe(viewLifecycleOwner,{subject ->
            adapter.setData(subject)
        })







        return binding.root
    }


}