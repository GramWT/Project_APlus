package com.example.project.Notifications.Lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.databinding.CustomLessonListRowBinding
import kotlinx.android.synthetic.main.custom_lesson_list_row.view.*

class SubLessonAdapter() : RecyclerView.Adapter<SubLessonAdapter.MyViewHolder>() {

    private lateinit var binding: CustomLessonListRowBinding


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomLessonListRowBinding.inflate(LayoutInflater.from(parent.context))

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.itemView.lesson_name.text = "set"
    }

    override fun getItemCount(): Int {
        return 3
    }
}