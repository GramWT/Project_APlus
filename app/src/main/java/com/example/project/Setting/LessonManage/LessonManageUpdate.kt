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
import androidx.navigation.fragment.navArgs
import com.example.project.DataBase.model.Lesson
import com.example.project.DataBase.viewmodel.LessonViewModel
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_lesson_manage_add.*
import kotlinx.android.synthetic.main.fragment_lesson_manage_add.lesson_name1_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_add.lesson_subject_name
import kotlinx.android.synthetic.main.fragment_lesson_manage_add.view.*
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.*
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.*
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.cancel_button_update
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name1
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_name1_text
import kotlinx.android.synthetic.main.fragment_lesson_manage_update.view.lesson_subject_name


class LessonManageUpdate : Fragment() {

    private val args by navArgs<LessonManageUpdateArgs>()
    private lateinit var mLessonViewModel: LessonViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_lesson_manage_update, container, false)

        mLessonViewModel = ViewModelProvider(this).get(LessonViewModel::class.java)


        view.cancel_button_update.setOnClickListener {
            val action = LessonManageUpdateDirections.actionLessonManageUpdateToLessonManage()
            findNavController().navigate(action)
        }

        view.add_lesson_update_button.setOnClickListener {
            addLesson(view)
        }

        view.save_update_lesson.setOnClickListener {
            insertDataToDatabase(args.Lesson)
        }

        view.lesson_subject_name.setText(args.Lesson.name)
        view.lesson_name1_text.setText(args.Lesson.l01)
        view.lesson_text_2.setText(args.Lesson.l02)
        view.lesson_text_3.setText(args.Lesson.l03)
        view.lesson_text_4.setText(args.Lesson.l04)
        view.lesson_text_5.setText(args.Lesson.l05)
        view.lesson_text_6.setText(args.Lesson.l06)
        view.lesson_text_7.setText(args.Lesson.l07)
        view.lesson_text_8.setText(args.Lesson.l08)
        view.lesson_text_9.setText(args.Lesson.l09)
        view.lesson_text_10.setText(args.Lesson.l10)
        view.lesson_text_11.setText(args.Lesson.l11)
        view.lesson_text_12.setText(args.Lesson.l12)
        view.lesson_text_13.setText(args.Lesson.l13)
        view.lesson_text_14.setText(args.Lesson.l14)
        view.lesson_text_15.setText(args.Lesson.l15)
        view.lesson_text_16.setText(args.Lesson.l16)

        view.remove_view_update_1.setOnClickListener {
            view.lesson_name1.visibility = View.GONE
            lesson_name1_text.setText("")
            checkAddButton(view)
        }
        view.remove_update_2.setOnClickListener {
            view.layout2.visibility = View.GONE
            lesson_text_2.setText("")
            checkAddButton(view)
        }
        view.remove_update_3.setOnClickListener {
            view.layout3.visibility = View.GONE
            lesson_text_3.setText("")
            checkAddButton(view)
        }
        view.remove_update_4.setOnClickListener {
            view.layout4.visibility = View.GONE
            lesson_text_4.setText("")
            checkAddButton(view)
        }
        view.remove_update_5.setOnClickListener {
            view.layout5.visibility = View.GONE
            lesson_text_5.setText("")
            checkAddButton(view)
        }
        view.remove_update_6.setOnClickListener {
            view.layout6.visibility = View.GONE
            lesson_text_6.setText("")
            checkAddButton(view)
        }
        view.remove_update_7.setOnClickListener {
            view.layout7.visibility = View.GONE
            lesson_text_7.setText("")
            checkAddButton(view)
        }
        view.remove_update_8.setOnClickListener {
            view.layout8.visibility = View.GONE
            lesson_text_8.setText("")
            checkAddButton(view)
        }
        view.remove_update_9.setOnClickListener {
            view.layout9.visibility = View.GONE
            lesson_text_9.setText("")
            checkAddButton(view)
        }
        view.remove_update_10.setOnClickListener {
            view.layout10.visibility = View.GONE
            lesson_text_10.setText("")
            checkAddButton(view)
        }
        view.remove_update_11.setOnClickListener {
            view.layout11.visibility = View.GONE
            lesson_text_11.setText("")
            checkAddButton(view)
        }
        view.remove_update_12.setOnClickListener {
            view.layout12.visibility = View.GONE
            lesson_text_12.setText("")
            checkAddButton(view)
        }
        view.remove_update_13.setOnClickListener {
            view.layout13.visibility = View.GONE
            lesson_text_13.setText("")
            checkAddButton(view)
        }
        view.remove_update_14.setOnClickListener {
            view.layout14.visibility = View.GONE
            lesson_text_14.setText("")
            checkAddButton(view)
        }
        view.remove_update_15.setOnClickListener {
            view.layout15.visibility = View.GONE
            lesson_text_15.setText("")
            checkAddButton(view)
        }
        view.remove_update_16.setOnClickListener {
            view.layout16.visibility = View.GONE
            lesson_text_16.setText("")
            checkAddButton(view)
        }



        if (view.lesson_name1_text.text.isEmpty()){
            view.lesson_name1.visibility = View.GONE
        }
        if (view.lesson_text_2.text.isEmpty()){
            view.layout2.visibility = View.GONE
        }
        if (view.lesson_text_3.text.isEmpty()){
            view.layout3.visibility = View.GONE
        }
        if (view.lesson_text_4.text.isEmpty()){
            view.layout4.visibility = View.GONE
        }
        if (view.lesson_text_4.text.isEmpty()){
            view.layout4.visibility = View.GONE
        }
        if (view.lesson_text_5.text.isEmpty()){
            view.layout5.visibility = View.GONE
        }
        if (view.lesson_text_6.text.isEmpty()){
            view.layout6.visibility = View.GONE
        }
        if (view.lesson_text_7.text.isEmpty()){
            view.layout7.visibility = View.GONE
        }
        if (view.lesson_text_8.text.isEmpty()){
            view.layout8.visibility = View.GONE
        }
        if (view.lesson_text_9.text.isEmpty()){
            view.layout9.visibility = View.GONE
        }
        if (view.lesson_text_10.text.isEmpty()){
            view.layout10.visibility = View.GONE
        }
        if (view.lesson_text_11.text.isEmpty()){
            view.layout11.visibility = View.GONE
        }
        if (view.lesson_text_12.text.isEmpty()){
            view.layout12.visibility = View.GONE
        }
        if (view.lesson_text_13.text.isEmpty()){
            view.layout13.visibility = View.GONE
        }
        if (view.lesson_text_14.text.isEmpty()){
            view.layout14.visibility = View.GONE
        }
        if (view.lesson_text_15.text.isEmpty()){
            view.layout15.visibility = View.GONE
        }
        if (view.lesson_text_16.text.isEmpty()){
            view.layout16.visibility = View.GONE
        }

        return view
    }


    private fun addLesson(view: View){
        if (view.lesson_name1.visibility == View.GONE){
            view.lesson_name1.visibility = View.VISIBLE
        }
        else if (view.layout2.visibility == View.GONE){
            view.layout2.visibility = View.VISIBLE
        }
        else if (view.layout3.visibility == View.GONE){
            view.layout3.visibility = View.VISIBLE
        }
        else if (view.layout4.visibility == View.GONE){
            view.layout4.visibility = View.VISIBLE
        }
        else if (view.layout5.visibility == View.GONE){
            view.layout5.visibility = View.VISIBLE
        }
        else if (view.layout6.visibility == View.GONE){
            view.layout6.visibility = View.VISIBLE
        }
        else if (view.layout7.visibility == View.GONE){
            view.layout7.visibility = View.VISIBLE
        }
        else if (view.layout8.visibility == View.GONE){
            view.layout8.visibility = View.VISIBLE
        }
        else if (view.layout9.visibility == View.GONE){
            view.layout9.visibility = View.VISIBLE
        }
        else if (view.layout10.visibility == View.GONE){
            view.layout10.visibility = View.VISIBLE
        }
        else if (view.layout11.visibility == View.GONE){
            view.layout11.visibility = View.VISIBLE
        }
        else if (view.layout12.visibility == View.GONE){
            view.layout12.visibility = View.VISIBLE
        }
        else if (view.layout13.visibility == View.GONE){
            view.layout13.visibility = View.VISIBLE
        }
        else if (view.layout14.visibility == View.GONE){
            view.layout14.visibility = View.VISIBLE
        }
        else if (view.layout15.visibility == View.GONE){
            view.layout15.visibility = View.VISIBLE
        }
        else if (view.layout16.visibility == View.GONE){
            view.layout16.visibility = View.VISIBLE

        }
        checkAddButton(view)
    }

    private fun checkAddButton(view: View){
        if (view.lesson_name1.visibility == View.GONE || view.layout2.visibility == View.GONE || view.layout3.visibility == View.GONE
                || view.layout4.visibility == View.GONE|| view.layout5.visibility == View.GONE|| view.layout6.visibility == View.GONE
                || view.layout7.visibility == View.GONE|| view.layout8.visibility == View.GONE|| view.layout9.visibility == View.GONE
                || view.layout10.visibility == View.GONE|| view.layout11.visibility == View.GONE|| view.layout12.visibility == View.GONE
                || view.layout13.visibility == View.GONE|| view.layout14.visibility == View.GONE|| view.layout15.visibility == View.GONE
                || view.layout16.visibility == View.GONE){

        }
        else{
            view.add_lesson_update_button.visibility = View.GONE
        }

    }

    private fun insertDataToDatabase(currentLesson:Lesson){

        val sid = lesson_subject_name.text.toString()
        val l01 = lesson_name1_text.text.toString()
        val l02 = lesson_text_2.text.toString()
        val l03 = lesson_text_3.text.toString()
        val l04 = lesson_text_4.text.toString()
        val l05 = lesson_text_5.text.toString()
        val l06 = lesson_text_6.text.toString()
        val l07 = lesson_text_7.text.toString()
        val l08 = lesson_text_8.text.toString()
        val l09 = lesson_text_9.text.toString()
        val l10 = lesson_text_10.text.toString()
        val l11 = lesson_text_11.text.toString()
        val l12 = lesson_text_12.text.toString()
        val l13 = lesson_text_13.text.toString()
        val l14 = lesson_text_14.text.toString()
        val l15 = lesson_text_15.text.toString()
        val l16 = lesson_text_16.text.toString()

        if (inputCheck(sid,l01)){
            val lesson = Lesson(currentLesson.id,sid,l01,l02,l03,l04,l05,l06,l07,l08,l09,l10,l11,l12,l13,l14,l15,l16,currentLesson.l01_C1,currentLesson.l02_C1,
                    currentLesson.l03_C1,currentLesson.l04_C1,currentLesson.l05_C1,currentLesson.l06_C1,currentLesson.l07_C1,currentLesson.l08_C1,currentLesson.l09_C1,
                    currentLesson.l10_C1,currentLesson.l11_C1,currentLesson.l12_C1,currentLesson.l13_C1,currentLesson.l14_C1,currentLesson.l15_C1,currentLesson.l16_C1,
                    currentLesson.l01_C2, currentLesson.l02_C2, currentLesson.l03_C2, currentLesson.l04_C2, currentLesson.l05_C2, currentLesson.l06_C2, currentLesson.l07_C2,
                    currentLesson.l08_C2, currentLesson.l09_C2, currentLesson.l10_C2, currentLesson.l11_C2, currentLesson.l12_C2, currentLesson.l13_C2, currentLesson.l14_C2,
                    currentLesson.l15_C2, currentLesson.l16_C2, currentLesson.l01_C3,currentLesson.l02_C3,currentLesson.l03_C3,currentLesson.l04_C3,currentLesson.l05_C3,currentLesson.l06_C3,
                    currentLesson.l07_C3,currentLesson.l08_C3,currentLesson.l09_C3,currentLesson.l10_C3,currentLesson.l11_C3,currentLesson.l12_C3,currentLesson.l13_C3,currentLesson.l14_C3,
                    currentLesson.l15_C3,currentLesson.l16_C3, currentLesson.l01_C4,currentLesson.l02_C4,currentLesson.l03_C4,currentLesson.l04_C4,currentLesson.l05_C4,currentLesson.l06_C4,
                    currentLesson.l07_C4,currentLesson.l08_C4,currentLesson.l09_C4,currentLesson.l10_C4,currentLesson.l11_C4,currentLesson.l12_C4,currentLesson.l13_C4,currentLesson.l14_C4,
                    currentLesson.l15_C4,currentLesson.l16_C4)

            mLessonViewModel.updateLesson(lesson)

            Toast.makeText(requireContext(),"Successfully add!", Toast.LENGTH_SHORT).show()

            val action = LessonManageUpdateDirections.actionLessonManageUpdateToLessonManage()
            findNavController().navigate(action)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields.", Toast.LENGTH_SHORT).show()


        }
    }

    private fun inputCheck(sid:String,titlename:String):Boolean{

        return  !(TextUtils.isEmpty(sid)|| TextUtils.isEmpty(titlename))

    }


}