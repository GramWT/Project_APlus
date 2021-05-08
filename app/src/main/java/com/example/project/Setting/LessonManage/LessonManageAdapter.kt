package com.example.project.Setting.LessonManage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DataBase.model.Lesson
import com.example.project.databinding.CustomLessonManageBinding
import kotlinx.android.synthetic.main.custom_lesson_manage.view.*

class LessonManageAdapter : RecyclerView.Adapter<LessonManageAdapter.MyViewHolder>() {

    private var lessonList = emptyList<Lesson>()
    private lateinit var binding: CustomLessonManageBinding

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomLessonManageBinding.inflate(LayoutInflater.from(parent.context))

        return MyViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = lessonList[position]

        holder.itemView.subject_name.text = currentItem.name

        holder.itemView.row_manage_lesson.setOnClickListener {
            val action = LessonManageDirections.actionLessonManageToLessonManageUpdate(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return lessonList.size
    }

    fun setData(lesson: List<Lesson>) {
        this.lessonList = lesson
        notifyDataSetChanged()
    }

}