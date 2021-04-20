package com.example.project.Setting.LessonManage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_lesson_manage_add.view.*
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.*
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.cancel_button_update
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name1
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name10_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name11_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name12_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name13_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name14_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name15_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name16_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name1_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name2_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name3_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name4_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name5_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name6_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name7_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name8_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name9_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_subject_name


class LessonManageUpdate : Fragment() {

    private val args by navArgs<LessonManageUpdateArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lesson_manage_update, container, false)

        view.cancel_button_update.setOnClickListener {
            val action = LessonManageUpdateDirections.actionLessonManageUpdateToLessonManage()
            findNavController().navigate(action)
        }

        view.lesson_subject_name.setText(args.Lesson.name)
        view.lesson_name1_text.setText(args.Lesson.l01)
        view.lesson_name2_text.setText(args.Lesson.l02)
        view.lesson_name3_text.setText(args.Lesson.l03)
        view.lesson_name4_text.setText(args.Lesson.l04)
        view.lesson_name5_text.setText(args.Lesson.l05)
        view.lesson_name6_text.setText(args.Lesson.l06)
        view.lesson_name7_text.setText(args.Lesson.l07)
        view.lesson_name8_text.setText(args.Lesson.l08)
        view.lesson_name9_text.setText(args.Lesson.l09)
        view.lesson_name10_text.setText(args.Lesson.l10)
        view.lesson_name11_text.setText(args.Lesson.l11)
        view.lesson_name12_text.setText(args.Lesson.l12)
        view.lesson_name13_text.setText(args.Lesson.l13)
        view.lesson_name14_text.setText(args.Lesson.l14)
        view.lesson_name15_text.setText(args.Lesson.l15)
        view.lesson_name16_text.setText(args.Lesson.l16)

        if (view.lesson_name1_text.text.isEmpty()){
            view.lesson_name1.visibility = View.GONE

        }


        return view
    }


}