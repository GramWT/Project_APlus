package com.example.project.Setting.SubjectsManage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_nav_subjects_manage.view.*


class NavSubjectsManage : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_nav_subjects_manage, container, false)

        return view
    }


}