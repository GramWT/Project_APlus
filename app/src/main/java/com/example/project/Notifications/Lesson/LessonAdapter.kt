package com.example.project.Notifications.Lesson

import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.project.DataBase.data.LessonDao
import com.example.project.DataBase.model.Lesson
import com.example.project.DataBase.viewmodel.LessonViewModel
import com.example.project.R
import com.example.project.databinding.CustomLessonRowBinding
import kotlinx.android.synthetic.main.custom_lesson_row.view.*
import kotlinx.android.synthetic.main.dialog_building_select.view.*
import kotlin.coroutines.coroutineContext

class LessonAdapter(var m:LessonViewModel,var mcontext:Context):RecyclerView.Adapter<LessonAdapter.MyViewHolder>() {

    private lateinit var binding: CustomLessonRowBinding
    private var lessonList = emptyList<Lesson>()


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomLessonRowBinding.inflate(LayoutInflater.from(parent.context))

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = lessonList[position]



        holder.itemView.lesson_subject_text.text = currentItem.name

        holder.itemView.lesson_subject_text.setOnClickListener {
            dialogBuilding()
        }


        val dd = ItemLessonCheck(currentItem.l01,currentItem.l02,currentItem.l03,currentItem.l04,currentItem.l05)
        val cc = arrayListOf<Int>(currentItem.l01_C,currentItem.l02_C,currentItem.l03_C,currentItem.l04_C,currentItem.l05_C)




        val aaa = SubLessonViewPagerAdapter(dd,cc,m,currentItem)


        holder.itemView.vpsub.adapter = aaa
        holder.itemView.indicate.setViewPager(holder.itemView.vpsub)



    }

    override fun getItemCount(): Int {
        return lessonList.size
    }

    fun setData(lesson: List<Lesson>){
        this.lessonList = lesson
        notifyDataSetChanged()
    }


    private fun ItemLessonCheck(string1: String,string2: String,string3: String,string4: String,string5: String): List<String>{
        var listItem:MutableList<String> = mutableListOf()
        if (inputCheck(string1)){
            listItem.add(string1)
        }
        if (inputCheck(string2)){
            listItem.add(string2)
        }
        if (inputCheck(string3)){
            listItem.add(string3)
        }
        if (inputCheck(string4)){
            listItem.add(string4)
        }
        if (inputCheck(string5)){
            listItem.add(string5)
        }
        return listItem
    }

    private fun inputCheck(string: String):Boolean{

        return  !(TextUtils.isEmpty(string))

    }

    private fun dialogBuilding(){
        val selectBuilding = LayoutInflater.from(mcontext).inflate(R.layout.dialog_building_select,null)
        val mBuilder = AlertDialog.Builder(mcontext)
                .setView(selectBuilding)

        val mAlert = mBuilder.show()


    }

}