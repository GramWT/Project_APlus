package com.example.project.Adapter

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.project.Notifications.Exam.Final.FinalExamNotification
import com.example.project.Notifications.Exam.Mid.MidExamNotification
import com.example.project.Notifications.Exam.Mid.NavMid
import com.example.project.R

class TabAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> {return NavMid()}
            1 -> {return FinalExamNotification()}
            else -> {return MidExamNotification()}
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {return "Mid"}
            1 -> {return "Final"}
        }
        return super.getPageTitle(position)
    }
}

