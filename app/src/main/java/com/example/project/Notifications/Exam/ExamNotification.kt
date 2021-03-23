package com.example.project.Notifications.Exam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project.Adapter.TabExamAdapter
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.model.User
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.DataBase.viewmodel.UserViewModel
import com.example.project.Notifications.Exam.Mid.MidExamNotification
import com.example.project.databinding.FragmentSubjectTabBinding
import com.google.android.material.tabs.TabLayoutMediator

class ExamNotification : Fragment() {

    lateinit var binding: FragmentSubjectTabBinding
    private lateinit var mSubjectModel:SubjectViewModel
    private lateinit var subjectList:List<Subject>
    private lateinit var mUserModel:UserViewModel
    private lateinit var userList:List<User>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mSubjectModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mUserModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mUserModel.readAllData.observe(viewLifecycleOwner,{user ->
            userList = user
            if (userList.isEmpty()){
                val action = ExamNotificationDirections.actionExamNotificationToCreateUser()
                findNavController().navigate(action)
            }
            else{
                mSubjectModel.readAllData.observe(viewLifecycleOwner,{subject ->
                    subjectList = subject

                    if (subjectList.isEmpty()){
                        val action = ExamNotificationDirections.actionExamNotificationToDirectToSubjectManagement()
                        findNavController().navigate(action)
                        println(subjectList.size)
                    }
                    else{

                        println(subjectList.size)
                    }
                })
            }
        })



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubjectTabBinding.inflate(layoutInflater)
        val tab = TabExamAdapter(childFragmentManager,lifecycle)


        binding.viewpager.adapter = tab

        TabLayoutMediator(binding.tablayout,binding.viewpager,true,true){tab,position->
            when(position){
                0-> {
                    tab.text = "Mid"
                }
                1 -> {
                    tab.text = "Final"
                }
            }
        }.attach()

        binding.viewpager.setSaveEnabled(false)

        return binding.root

    }





}