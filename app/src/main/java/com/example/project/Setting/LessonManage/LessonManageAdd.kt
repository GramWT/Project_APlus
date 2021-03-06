package com.example.project.Setting.LessonManage

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project.DataBase.model.Lesson
import com.example.project.DataBase.viewmodel.LessonViewModel
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_lesson_manage_add.*
import kotlinx.android.synthetic.main.fragment_lesson_manage_add.view.*


class LessonManageAdd : Fragment() {

    private lateinit var mLessonViewModel: LessonViewModel
    private lateinit var mLesson: List<Lesson>


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lesson_manage_add, container, false)

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

        view.remove_view1.setOnClickListener {
            sortText1(view)
            sortLessonAdd(view)
            checkAddButton(view)

        }
        view.remove_view2.setOnClickListener {
            sortText2(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view3.setOnClickListener {
            sortText3(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view4.setOnClickListener {
            sortText4(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view5.setOnClickListener {
            sortText5(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view6.setOnClickListener {
            sortText6(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view7.setOnClickListener {
            sortText7(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view8.setOnClickListener {
            sortText8(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view9.setOnClickListener {
            sortText9(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view10.setOnClickListener {
            sortText10(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view11.setOnClickListener {
            sortText11(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view12.setOnClickListener {
            sortText12(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view13.setOnClickListener {
            sortText13(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view14.setOnClickListener {
            sortText14(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view15.setOnClickListener {
            sortText15(view)
            sortLessonAdd(view)
            checkAddButton(view)
        }
        view.remove_view16.setOnClickListener {
            sortLessonAdd(view)
            checkAddButton(view)
        }


        mLessonViewModel = ViewModelProvider(this).get(LessonViewModel::class.java)

        return view
    }

    private fun sortText1(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
        } else if (view.lesson_name11.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
        } else if (view.lesson_name10.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
        } else if (view.lesson_name9.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
        } else if (view.lesson_name8.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
        } else if (view.lesson_name7.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
        } else if (view.lesson_name6.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
        } else if (view.lesson_name5.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
        } else if (view.lesson_name4.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
        } else if (view.lesson_name3.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
        } else if (view.lesson_name2.visibility == View.VISIBLE) {
            view.lesson_name1_text.setText(view.lesson_name2_text.text.toString())
        }
    }

    private fun sortText2(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
        } else if (view.lesson_name11.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
        } else if (view.lesson_name10.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
        } else if (view.lesson_name9.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
        } else if (view.lesson_name8.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
        } else if (view.lesson_name7.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
        } else if (view.lesson_name6.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
        } else if (view.lesson_name5.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
        } else if (view.lesson_name4.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
        } else if (view.lesson_name3.visibility == View.VISIBLE) {
            view.lesson_name2_text.setText(view.lesson_name3_text.text.toString())
        }
    }

    private fun sortText3(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
        } else if (view.lesson_name11.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
        } else if (view.lesson_name10.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
        } else if (view.lesson_name9.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
        } else if (view.lesson_name8.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
        } else if (view.lesson_name7.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
        } else if (view.lesson_name6.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
        } else if (view.lesson_name5.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
        } else if (view.lesson_name4.visibility == View.VISIBLE) {
            view.lesson_name3_text.setText(view.lesson_name4_text.text.toString())
        }
    }

    private fun sortText4(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
        } else if (view.lesson_name11.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
        } else if (view.lesson_name10.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
        } else if (view.lesson_name9.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
        } else if (view.lesson_name8.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
        } else if (view.lesson_name7.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
        } else if (view.lesson_name6.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
        } else if (view.lesson_name5.visibility == View.VISIBLE) {
            view.lesson_name4_text.setText(view.lesson_name5_text.text.toString())
        }
    }

    private fun sortText5(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
        } else if (view.lesson_name11.visibility == View.VISIBLE) {
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
        } else if (view.lesson_name10.visibility == View.VISIBLE) {
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
        } else if (view.lesson_name9.visibility == View.VISIBLE) {
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
        } else if (view.lesson_name8.visibility == View.VISIBLE) {
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
        } else if (view.lesson_name7.visibility == View.VISIBLE) {
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
        } else if (view.lesson_name6.visibility == View.VISIBLE) {
            view.lesson_name5_text.setText(view.lesson_name6_text.text.toString())
        }
    }

    private fun sortText6(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
        } else if (view.lesson_name11.visibility == View.VISIBLE) {
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
        } else if (view.lesson_name10.visibility == View.VISIBLE) {
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
        } else if (view.lesson_name9.visibility == View.VISIBLE) {
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
        } else if (view.lesson_name8.visibility == View.VISIBLE) {
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
        } else if (view.lesson_name7.visibility == View.VISIBLE) {
            view.lesson_name6_text.setText(view.lesson_name7_text.text.toString())
        }
    }

    private fun sortText7(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
        } else if (view.lesson_name11.visibility == View.VISIBLE) {
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
        } else if (view.lesson_name10.visibility == View.VISIBLE) {
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
        } else if (view.lesson_name9.visibility == View.VISIBLE) {
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
        } else if (view.lesson_name8.visibility == View.VISIBLE) {
            view.lesson_name7_text.setText(view.lesson_name8_text.text.toString())
        }
    }

    private fun sortText8(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
        } else if (view.lesson_name11.visibility == View.VISIBLE) {
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
        } else if (view.lesson_name10.visibility == View.VISIBLE) {
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
        } else if (view.lesson_name9.visibility == View.VISIBLE) {
            view.lesson_name8_text.setText(view.lesson_name9_text.text.toString())
        }
    }

    private fun sortText9(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
        } else if (view.lesson_name11.visibility == View.VISIBLE) {
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
        } else if (view.lesson_name10.visibility == View.VISIBLE) {
            view.lesson_name9_text.setText(view.lesson_name10_text.text.toString())
        }
    }

    private fun sortText10(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
        } else if (view.lesson_name11.visibility == View.VISIBLE) {
            view.lesson_name10_text.setText(view.lesson_name11_text.text.toString())
        }
    }

    private fun sortText11(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name11_text.setText(view.lesson_name12_text.text.toString())
        }
    }

    private fun sortText12(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name12_text.setText(view.lesson_name13_text.text.toString())
        }
    }

    private fun sortText13(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name13_text.setText(view.lesson_name14_text.text.toString())
        }
    }

    private fun sortText14(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name14_text.setText(view.lesson_name15_text.text.toString())
        }
    }

    private fun sortText15(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name15_text.setText(view.lesson_name16_text.text.toString())
        }
    }


    private fun sortLessonAdd(view: View) {
        if (view.lesson_name16.visibility == View.VISIBLE) {
            view.lesson_name16.visibility = View.GONE
            view.lesson_name16_text.setText("")
        } else if (view.lesson_name15.visibility == View.VISIBLE) {
            view.lesson_name15.visibility = View.GONE
            view.lesson_name15_text.setText("")
        } else if (view.lesson_name14.visibility == View.VISIBLE) {
            view.lesson_name14.visibility = View.GONE
            view.lesson_name14_text.setText("")
        } else if (view.lesson_name13.visibility == View.VISIBLE) {
            view.lesson_name13.visibility = View.GONE
            view.lesson_name13_text.setText("")
        } else if (view.lesson_name12.visibility == View.VISIBLE) {
            view.lesson_name12.visibility = View.GONE
            view.lesson_name12_text.setText("")
        } else if (view.lesson_name11.visibility == View.VISIBLE) {
            view.lesson_name11.visibility = View.GONE
            view.lesson_name11_text.setText("")
        } else if (view.lesson_name10.visibility == View.VISIBLE) {
            view.lesson_name10.visibility = View.GONE
            view.lesson_name10_text.setText("")
        } else if (view.lesson_name9.visibility == View.VISIBLE) {
            view.lesson_name9.visibility = View.GONE
            view.lesson_name9_text.setText("")
        } else if (view.lesson_name8.visibility == View.VISIBLE) {
            view.lesson_name8.visibility = View.GONE
            view.lesson_name8_text.setText("")
        } else if (view.lesson_name7.visibility == View.VISIBLE) {
            view.lesson_name7.visibility = View.GONE
            view.lesson_name7_text.setText("")
        } else if (view.lesson_name6.visibility == View.VISIBLE) {
            view.lesson_name6.visibility = View.GONE
            view.lesson_name6_text.setText("")
        } else if (view.lesson_name5.visibility == View.VISIBLE) {
            view.lesson_name5.visibility = View.GONE
            view.lesson_name5_text.setText("")
        } else if (view.lesson_name4.visibility == View.VISIBLE) {
            view.lesson_name4.visibility = View.GONE
            view.lesson_name4_text.setText("")
        } else if (view.lesson_name3.visibility == View.VISIBLE) {
            view.lesson_name3.visibility = View.GONE
            view.lesson_name3_text.setText("")
        } else if (view.lesson_name2.visibility == View.VISIBLE) {
            view.lesson_name2.visibility = View.GONE
            view.lesson_name2_text.setText("")
        } else if (view.lesson_name1.visibility == View.VISIBLE) {
            view.lesson_name1.visibility = View.GONE
            view.lesson_name1_text.setText("")

        }
    }

    private fun checkAddButton(view: View) {
        if (view.lesson_name1.visibility == View.GONE || view.lesson_name2.visibility == View.GONE || view.lesson_name3.visibility == View.GONE
                || view.lesson_name4.visibility == View.GONE || view.lesson_name5.visibility == View.GONE || view.lesson_name6.visibility == View.GONE
                || view.lesson_name7.visibility == View.GONE || view.lesson_name8.visibility == View.GONE || view.lesson_name9.visibility == View.GONE
                || view.lesson_name10.visibility == View.GONE || view.lesson_name11.visibility == View.GONE || view.lesson_name12.visibility == View.GONE
                || view.lesson_name13.visibility == View.GONE || view.lesson_name14.visibility == View.GONE || view.lesson_name15.visibility == View.GONE
                || view.lesson_name16.visibility == View.GONE) {
            view.add_lesson_button.visibility = View.VISIBLE
        } else {
            view.add_lesson_button.visibility = View.GONE
        }

    }


    private fun addLesson(view: View) {
        if (view.lesson_name1.visibility == View.GONE) {
            view.lesson_name1.visibility = View.VISIBLE
        } else if (view.lesson_name2.visibility == View.GONE) {
            view.lesson_name2.visibility = View.VISIBLE
        } else if (view.lesson_name3.visibility == View.GONE) {
            view.lesson_name3.visibility = View.VISIBLE
        } else if (view.lesson_name4.visibility == View.GONE) {
            view.lesson_name4.visibility = View.VISIBLE
        } else if (view.lesson_name5.visibility == View.GONE) {
            view.lesson_name5.visibility = View.VISIBLE
        } else if (view.lesson_name6.visibility == View.GONE) {
            view.lesson_name6.visibility = View.VISIBLE
        } else if (view.lesson_name7.visibility == View.GONE) {
            view.lesson_name7.visibility = View.VISIBLE
        } else if (view.lesson_name8.visibility == View.GONE) {
            view.lesson_name8.visibility = View.VISIBLE
        } else if (view.lesson_name9.visibility == View.GONE) {
            view.lesson_name9.visibility = View.VISIBLE
        } else if (view.lesson_name10.visibility == View.GONE) {
            view.lesson_name10.visibility = View.VISIBLE
        } else if (view.lesson_name11.visibility == View.GONE) {
            view.lesson_name11.visibility = View.VISIBLE
        } else if (view.lesson_name12.visibility == View.GONE) {
            view.lesson_name12.visibility = View.VISIBLE
        } else if (view.lesson_name13.visibility == View.GONE) {
            view.lesson_name13.visibility = View.VISIBLE
        } else if (view.lesson_name14.visibility == View.GONE) {
            view.lesson_name14.visibility = View.VISIBLE
        } else if (view.lesson_name15.visibility == View.GONE) {
            view.lesson_name15.visibility = View.VISIBLE
        } else if (view.lesson_name16.visibility == View.GONE) {
            view.lesson_name16.visibility = View.VISIBLE

        }
        checkAddButton(view)
    }

    private fun setLessonVisible(view: View) {

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


    private fun insertDataToDatabase() {

        val sid = lesson_subject_name.text.toString()
        val l01 = lesson_name1_text.text.toString()
        val l02 = lesson_name2_text.text.toString()
        val l03 = lesson_name3_text.text.toString()
        val l04 = lesson_name4_text.text.toString()
        val l05 = lesson_name5_text.text.toString()
        val l06 = lesson_name6_text.text.toString()
        val l07 = lesson_name7_text.text.toString()
        val l08 = lesson_name8_text.text.toString()
        val l09 = lesson_name9_text.text.toString()
        val l10 = lesson_name10_text.text.toString()
        val l11 = lesson_name11_text.text.toString()
        val l12 = lesson_name12_text.text.toString()
        val l13 = lesson_name13_text.text.toString()
        val l14 = lesson_name14_text.text.toString()
        val l15 = lesson_name15_text.text.toString()
        val l16 = lesson_name16_text.text.toString()

        val rid = randomId()

        if (inputCheck(sid, l01)) {
            val lesson = Lesson(rid, sid, l01, l02, l03, l04, l05, l06, l07, l08, l09, l10, l11, l12, l13, l14, l15, l16, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

            mLessonViewModel.addLesson(lesson)

            Toast.makeText(requireContext(), "Successfully add!", Toast.LENGTH_SHORT).show()

            val action = LessonManageAddDirections.actionLessonManageAddToLessonManage()
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()


        }
    }

    private fun inputCheck(sid: String, titlename: String): Boolean {

        return !(TextUtils.isEmpty(sid) || TextUtils.isEmpty(titlename))

    }

    private fun randomId(): Int {
        var randomId = "2${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}".toInt()
        val list = arrayListOf<Int>()
        var checked = false

        mLessonViewModel.readAllData.observe(viewLifecycleOwner, { lesson ->
            mLesson = lesson

            for (i in 0..mLesson.size - 1) {

                list.add(mLesson[i].id)
            }

            while (checked == false) {
                if (randomId in list) {
                    randomId = "2${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}".toInt()
                    println(randomId)
                } else {
                    checked = true
                }
            }
        })
        return randomId
    }

}