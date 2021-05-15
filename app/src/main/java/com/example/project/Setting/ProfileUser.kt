package com.example.project.Setting

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.model.User
import com.example.project.DataBase.viewmodel.*
import com.example.project.MainActivity
import com.example.project.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_change_avatar.view.*
import kotlinx.android.synthetic.main.fragment_profile_user.view.*


class ProfileUser : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mCalendarViewModel: EventCalendarViewModel
    private lateinit var mLessonViewModel: LessonViewModel
    private lateinit var mEventViewModel: EventViewModel
    private lateinit var mSubjectViewModel: SubjectViewModel
    private lateinit var mAlarmService: AlarmService
    private lateinit var mSubject: List<Subject>


    override fun onResume() {
        super.onResume()
        val a = activity as MainActivity
        a.bottom_navigation.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        val a = activity as MainActivity
        a.bottom_navigation.visibility = View.VISIBLE
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_profile_user, container, false)
        layout.profileUser.setImageResource(R.drawable.ic_man)

        mAlarmService = AlarmService(requireContext())
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mLessonViewModel = ViewModelProvider(this).get(LessonViewModel::class.java)
        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mCalendarViewModel = ViewModelProvider(this).get(EventCalendarViewModel::class.java)


        checkAvatar(layout)

        layout.delete_user.setOnClickListener {
            deleteUser()
        }

        layout.update_profile.setOnClickListener {

            mUserViewModel.readAllData.observe(viewLifecycleOwner, { user ->
                val newUser = User(9999, layout.user_name.text.toString(), user[0].avatar)
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

    private fun changeAvatar(layout: View) {
        val selectAvatar = LayoutInflater.from(context).inflate(R.layout.dialog_change_avatar, null)
        val mBuilder = AlertDialog.Builder(context)
                .setView(selectAvatar)

        val mAlert = mBuilder.show()

        selectAvatar.man.setOnClickListener {
            val user = User(9999, layout.user_name.text.toString(), 0)
            mUserViewModel.updateUser(user)
            checkAvatar(layout)
            mAlert.dismiss()
        }

        selectAvatar.woman.setOnClickListener {
            val user = User(9999, layout.user_name.text.toString(), 1)
            mUserViewModel.updateUser(user)
            checkAvatar(layout)
            mAlert.dismiss()
        }


    }

    fun checkAvatar(layout: View) {
        mUserViewModel.readAllData.observe(viewLifecycleOwner, { user ->
            layout.user_name.setText(user[0].name)

            if (user[0].avatar == 0) {
                layout.profileUser.setImageResource(R.drawable.ic_man)
            } else if (user[0].avatar == 1) {
                layout.profileUser.setImageResource(R.drawable.ic_woman)
            }

        })
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            mSubjectViewModel.readAllData.observe(viewLifecycleOwner, { subject ->
                mSubject = subject
                for (i in 0..mSubject.size - 1) {

                    val idMid: Int = "1${mSubject[i].id}".toInt()
                    val idFinal: Int = "2${mSubject[i].id}".toInt()


                    println("Delete ID :${mSubject[i].id}")
                    mAlarmService.cancelExamAlarm(idMid)
                    mAlarmService.cancelExamAlarm(idFinal)
                }

            })

            mCalendarViewModel.deleteAllEventDatabase()
            mLessonViewModel.deleteAllLesson()
            mSubjectViewModel.deleteAllSubject()
            mEventViewModel.deleteAllEvent()
            mUserViewModel.deleteAllUser()

            val a = activity as MainActivity
            a.bottom_navigation.selectedItemId = R.id.menu_exam

            Toast.makeText(
                    requireContext(),
                    "Successfully Delete User",
                    Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete User ?")
        builder.setMessage("Are you sure you want to delete User?")
        builder.create().show()

    }

}