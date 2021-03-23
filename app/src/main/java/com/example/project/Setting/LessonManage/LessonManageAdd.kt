package com.example.project.Setting.LessonManage

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project.DataBase.model.Lesson
import com.example.project.DataBase.viewmodel.LessonViewModel
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_lesson_manage_add.*
import kotlinx.android.synthetic.main.fragment_lesson_manage_add.view.*


class LessonManageAdd : Fragment() {

    private lateinit var mLessonViewModel: LessonViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_lesson_manage_add, container, false)

        setLessonVisible(view)

        view.cancel_button_update.setOnClickListener {
            val action = LessonManageAddDirections.actionLessonManageAddToLessonManage()
            findNavController().navigate(action)
        }

        view.add_lesson_button.setOnClickListener {
            addLesson(view)
        }

        view.admit_button_update.setOnClickListener {

            insertDataToDatabase()

        }

        mLessonViewModel = ViewModelProvider(this).get(LessonViewModel::class.java)

        return view
    }

    private fun addLesson(view: View){
        if (view.lesson_name2.visibility == View.GONE){
            view.lesson_name2.visibility = View.VISIBLE
        }
        else if (view.lesson_name3.visibility == View.GONE){
            view.lesson_name3.visibility = View.VISIBLE
        }
        else if (view.lesson_name4.visibility == View.GONE){
            view.lesson_name4.visibility = View.VISIBLE
        }
        else if (view.lesson_name5.visibility == View.GONE){
            view.lesson_name5.visibility = View.VISIBLE
        }
        else if (view.lesson_name6.visibility == View.GONE){
            view.lesson_name6.visibility = View.VISIBLE
        }
        else if (view.lesson_name7.visibility == View.GONE){
            view.lesson_name7.visibility = View.VISIBLE
        }
        else if (view.lesson_name8.visibility == View.GONE){
            view.lesson_name8.visibility = View.VISIBLE
        }
        else if (view.lesson_name9.visibility == View.GONE){
            view.lesson_name9.visibility = View.VISIBLE
        }
        else if (view.lesson_name10.visibility == View.GONE){
            view.lesson_name10.visibility = View.VISIBLE
        }
        else if (view.lesson_name11.visibility == View.GONE){
            view.lesson_name11.visibility = View.VISIBLE
        }
        else if (view.lesson_name12.visibility == View.GONE){
            view.lesson_name12.visibility = View.VISIBLE
        }
        else if (view.lesson_name13.visibility == View.GONE){
            view.lesson_name13.visibility = View.VISIBLE
        }
        else if (view.lesson_name14.visibility == View.GONE){
            view.lesson_name14.visibility = View.VISIBLE
        }
        else if (view.lesson_name15.visibility == View.GONE){
            view.lesson_name15.visibility = View.VISIBLE
        }
        else if (view.lesson_name16.visibility == View.GONE){
            view.lesson_name16.visibility = View.VISIBLE
        }



    }

    private fun setLessonVisible(view: View){

        view.lesson_name1.visibility = View.VISIBLE
        view.lesson_name2.visibility = View.GONE
        view.lesson_name3.visibility = View.GONE
        view.lesson_name4.visibility = View.GONE
        view.lesson_name5.visibility = View.GONE
        view.lesson_name6.visibility = View.GONE
        view.lesson_name7.visibility = View.GONE
        view.lesson_name8.visibility = View.GONE
        view.lesson_name9.visibility = View.GONE
        view.lesson_name10.visibility = View.GONE
        view.lesson_name11.visibility = View.GONE
        view.lesson_name12.visibility = View.GONE
        view.lesson_name13.visibility = View.GONE
        view.lesson_name14.visibility = View.GONE
        view.lesson_name15.visibility = View.GONE
        view.lesson_name16.visibility = View.GONE


    }


    private fun insertDataToDatabase(){

        val sid = lesson_subject_name.text.toString()
        val l01 = lesson_name1.text.toString()
        val l02 = lesson_name2.text.toString()
        val l03 = lesson_name3.text.toString()
        val l04 = lesson_name4.text.toString()
        val l05 = lesson_name5.text.toString()
        val l06 = lesson_name6.text.toString()
        val l07 = lesson_name7.text.toString()
        val l08 = lesson_name8.text.toString()
        val l09 = lesson_name9.text.toString()
        val l10 = lesson_name10.text.toString()

        if (inputCheck(sid,l01)){
            val lesson = Lesson(0,sid,l01,l02,l03,l04,l05,l06,l07,l08,l09,l10,0,0,0,0,0,0,0,0,0,0)

            mLessonViewModel.addLesson(lesson)

            Toast.makeText(requireContext(),"Successfully add!", Toast.LENGTH_SHORT).show()

            val action = LessonManageAddDirections.actionLessonManageAddToLessonManage()
            findNavController().navigate(action)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields.", Toast.LENGTH_SHORT).show()


        }
    }

    private fun inputCheck(sid:String,titlename:String):Boolean{

        return  !(TextUtils.isEmpty(sid)|| TextUtils.isEmpty(titlename))

    }

}