package com.example.project.Notifications.Lesson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_direct_to_lesson_management.view.*

class DirectToLessonManagement : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_direct_to_lesson_management, container, false)

        view.goto_lesson_management.setOnClickListener {

            val action =
                DirectToLessonManagementDirections.actionDirectToLessonManagementToNavLessonManage3()
            findNavController().navigate(action)

        }

        return view
    }

}