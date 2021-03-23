package com.example.project.Notifications.Lesson

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.project.DataBase.data.LessonDao
import com.example.project.DataBase.model.Lesson
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.LessonViewModel
import com.example.project.MainActivity
import com.example.project.R
import com.example.project.databinding.CustomLessonListRowBinding
import kotlinx.android.synthetic.main.custom_lesson_list_row.view.*
import kotlinx.android.synthetic.main.custom_lesson_row.view.*

class SubLessonViewPagerAdapter(val l:List<String>, val ll:List<Int>,val LM:LessonViewModel,val a:Lesson
):RecyclerView.Adapter<SubLessonViewPagerAdapter.ViewPagerViewHolder>(){

    private lateinit var binding: CustomLessonListRowBinding


    inner class ViewPagerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        binding = CustomLessonListRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)


        return ViewPagerViewHolder(binding.root)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.itemView.lesson_name.text = l[position]



        if (ll[position] == 1){
            holder.itemView.checkBox.isChecked = true
        }
        else if (ll[position] == 0){
            holder.itemView.checkBox.isChecked = false
        }





        holder.itemView.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked && position == 0){
                println("Checked")
            }
            else if(!isChecked && position == 0){
                println("Unchecked")
            }
            else if(isChecked && position == 1){


            }
            else if(!isChecked && position == 1){
                println("Unchecked")
            }

        }

    }

    override fun getItemCount(): Int {
        return  l.size
    }


}