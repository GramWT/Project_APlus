package com.example.project

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project.DataBase.model.User
import com.example.project.DataBase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.dialog_change_avatar.view.*
import kotlinx.android.synthetic.main.fragment_create__user.view.*


class Create_User : Fragment() {

    private var avatar = 0

    private lateinit var mUserModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_create__user, container, false)

        mUserModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val a = activity as MainActivity
        a.hideBottomNav()

        view.save_user.setOnClickListener {
            println(view.user_name.text.toString())

            if (view.user_name.text.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill up", Toast.LENGTH_SHORT).show()
            } else {
                var user = User(9999, view.user_name.text.toString(), avatar)
                mUserModel.addUser(user)
                val action = Create_UserDirections.actionCreateUserToExamNotification()
                findNavController().navigate(action)
                a.showBottomNav()
            }
        }

        view.change_avatar_button.setOnClickListener {
            changeAvatar(view)
        }

        return view
    }


    private fun changeAvatar(view: View) {
        val selectAvatar = LayoutInflater.from(context).inflate(R.layout.dialog_change_avatar, null)
        val mBuilder = AlertDialog.Builder(context)
                .setView(selectAvatar)

        val mAlert = mBuilder.show()

        selectAvatar.man.setOnClickListener {
            avatar = 0
            view.user_picture.setImageResource(R.drawable.ic_man)
            mAlert.dismiss()
        }

        selectAvatar.woman.setOnClickListener {
            avatar = 1
            view.user_picture.setImageResource(R.drawable.ic_woman)
            mAlert.dismiss()
        }


    }

}