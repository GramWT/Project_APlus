package com.example.project.Setting

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project.DataBase.model.User
import com.example.project.DataBase.viewmodel.UserViewModel
import com.example.project.R
import kotlinx.android.synthetic.main.dialog_change_avatar.view.*
import kotlinx.android.synthetic.main.fragment_profile_user.view.*


class ProfileUser : Fragment() {

    private lateinit var mUserViewModel: UserViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_profile_user, container, false)
        layout.profileUser.setImageResource(R.drawable.ic_man)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        checkAvatar(layout)

        layout.update_profile.setOnClickListener {

            mUserViewModel.readAllData.observe(viewLifecycleOwner,{user ->
                val newUser = User(9999,layout.user_name.text.toString(),user[0].avatar)
                mUserViewModel.updateUser(newUser)

            })

            val action = ProfileUserDirections.actionProfileUserToSetting()
            findNavController().navigate(action)
        }

        layout.back_profile.setOnClickListener {
            val action = ProfileUserDirections.actionProfileUserToSetting()
            findNavController().navigate(action)
        }

        layout.select_avatar.setOnClickListener {
            changeAvatar(layout)
        }


        return layout
    }

    private fun changeAvatar(layout: View){
        val selectAvatar = LayoutInflater.from(context).inflate(R.layout.dialog_change_avatar,null)
        val mBuilder = AlertDialog.Builder(context)
                .setView(selectAvatar)

        val mAlert = mBuilder.show()

        selectAvatar.man.setOnClickListener {
            val user = User(9999,layout.user_name.text.toString(),0)
            mUserViewModel.updateUser(user)
            checkAvatar(layout)
            mAlert.dismiss()
        }

        selectAvatar.woman.setOnClickListener {
            val user = User(9999,layout.user_name.text.toString(),1)
            mUserViewModel.updateUser(user)
            checkAvatar(layout)
            mAlert.dismiss()
        }



    }

    fun checkAvatar(layout:View){
        mUserViewModel.readAllData.observe(viewLifecycleOwner,{user ->
            layout.user_name.setText(user[0].name)

            if (user[0].avatar == 0){
                layout.profileUser.setImageResource(R.drawable.ic_man)
            }
            else if (user[0].avatar == 1){
                layout.profileUser.setImageResource(R.drawable.ic_woman)
            }

        })
    }

}