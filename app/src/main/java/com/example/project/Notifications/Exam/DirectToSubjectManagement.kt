package com.example.project.Notifications.Exam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_direct_to_subject_management.view.*


class DirectToSubjectManagement : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_direct_to_subject_management, container, false)

        view.goto_subject_management.setOnClickListener {

            val aa = DirectToSubjectManagementDirections.actionDirectToSubjectManagementToNavSubjectsManage3()
            findNavController().navigate(aa)
        }

        return view
    }

}