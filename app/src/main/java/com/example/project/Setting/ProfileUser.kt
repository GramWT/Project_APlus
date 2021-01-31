package com.example.project.Setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_profile_user.view.*


class ProfileUser : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_profile_user, container, false)

        layout.profile_user_setting.setOnClickListener {
            val action = ProfileUserDirections.actionProfileUserToSetting()
            findNavController().navigate(action)
        }
        return layout
    }

}