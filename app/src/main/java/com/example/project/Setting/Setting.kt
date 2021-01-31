package com.example.project.Setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.R
import com.example.project.databinding.FragmentSettingBinding
import com.example.project.databinding.FragmentSubjectTabBinding


class Setting : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private lateinit var mSubjectViewModel:SubjectViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSettingBinding.inflate(layoutInflater)

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)



        binding.subjectsManageSetting.setOnClickListener {
            val action = SettingDirections.actionSettingToNavSubjectsManage()
            findNavController().navigate(action)

        }

        binding.profileUser.setOnClickListener {
            val action = SettingDirections.actionSettingToProfileUser()
            findNavController().navigate(action)
        }


        return binding.root
    }


}