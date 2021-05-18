package com.example.project.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.project.Notifications.Exam.Final.FinalExamNotification
import com.example.project.Notifications.Exam.Mid.MidExamNotification
import com.example.project.Notifications.Exam.Mid.NavMid

class TabExamAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                NavMid()
            }
            1 -> {
                FinalExamNotification()
            }
            else -> {
                MidExamNotification()
            }
        }
    }

}