package com.example.project.Notifications.Lesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.project.MainActivity
import com.example.project.Notifications.Exam.DirectToSubjectManagementDirections
import com.example.project.Notifications.Exam.ExamNotificationDirections
import com.example.project.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_direct_to_lesson_management.view.*

class DirectToLessonManagement : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_direct_to_lesson_management, container, false)

        view.goto_lesson_management.setOnClickListener {

            val aa = DirectToLessonManagementDirections.actionDirectToLessonManagementToNavLessonManage3()
            findNavController().navigate(aa)

        }

        return view
    }

}