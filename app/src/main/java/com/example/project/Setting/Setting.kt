package com.example.project.Setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.model.User
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.DataBase.viewmodel.UserViewModel
import com.example.project.R
import com.example.project.databinding.FragmentSettingBinding
import com.example.project.databinding.FragmentSubjectTabBinding


class Setting : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private lateinit var mSubjectViewModel:SubjectViewModel
    private lateinit var mUserViewModel:UserViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSettingBinding.inflate(layoutInflater)

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mUserViewModel.readAllData.observe(viewLifecycleOwner,{user ->
            if (user[0].avatar == 0){
                binding.avatar.setImageResource(R.drawable.ic_man)
            }
            else if (user[0].avatar == 1){
                binding.avatar.setImageResource(R.drawable.ic_woman)
            }

            binding.textUsername.setText(user[0].name)
        })


        binding.subjectsManageSetting.setOnClickListener {
            val action = SettingDirections.actionSettingToNavSubjectsManage()
            findNavController().navigate(action)

        }

        binding.profileUser.setOnClickListener {
            val action = SettingDirections.actionSettingToProfileUser()
            findNavController().navigate(action)
        }

        binding.credit.setOnClickListener {
            val action = SettingDirections.actionSettingToCreditDeveloper2()
            findNavController().navigate(action)
        }

        binding.lessonManageSetting.setOnClickListener {
            val action = SettingDirections.actionSettingToNavLessonManage()
            findNavController().navigate(action)
        }

        binding.preference.setOnClickListener {
            val action = SettingDirections.actionSettingToPreferenceView()
            findNavController().navigate(action)


        }


        return binding.root
    }


}