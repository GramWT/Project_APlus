package com.example.project.Notifications.Lesson

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DataBase.model.Lesson
import com.example.project.databinding.CustomLessonListRowBinding
import kotlinx.android.synthetic.main.custom_lesson_list_row.view.*

class SubLessonViewPagerAdapter(private val lessonList: List<String>, private val CheckLesson_1: List<Int>, private val CheckLesson_2: List<Int>,
                                private val CheckLesson_3: List<Int>, private val CheckLesson_4: List<Int>, val currentLesson: Lesson
) : RecyclerView.Adapter<SubLessonViewPagerAdapter.ViewPagerViewHolder>() {

    private lateinit var binding: CustomLessonListRowBinding


    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        binding = CustomLessonListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)


        return ViewPagerViewHolder(binding.root)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.itemView.lesson_name.text = lessonList[position]


        if (CheckLesson_1[position] == 1) {
            holder.itemView.checkBox1.isChecked = true
        } else if (CheckLesson_1[position] == 0) {
            holder.itemView.checkBox1.isChecked = false
        }

        if (CheckLesson_2[position] == 1) {
            holder.itemView.checkBox2.isChecked = true
        } else if (CheckLesson_2[position] == 0) {
            holder.itemView.checkBox2.isChecked = false
        }

        if (CheckLesson_3[position] == 1) {
            holder.itemView.checkBox3.isChecked = true
        } else if (CheckLesson_3[position] == 0) {
            holder.itemView.checkBox3.isChecked = false
        }

        if (CheckLesson_4[position] == 1) {
            holder.itemView.checkBox4.isChecked = true
        } else if (CheckLesson_4[position] == 0) {
            holder.itemView.checkBox4.isChecked = false
        }


    }

    override fun getItemCount(): Int {
        return lessonList.size
    }


}