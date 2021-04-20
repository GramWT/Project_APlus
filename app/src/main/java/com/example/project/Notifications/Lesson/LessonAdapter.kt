package com.example.project.Notifications.Lesson

import android.app.AlertDialog
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.size
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DataBase.model.Lesson
import com.example.project.DataBase.viewmodel.LessonViewModel
import com.example.project.R
import com.example.project.databinding.CustomLessonRowBinding
import kotlinx.android.synthetic.main.custom_lesson_row.view.*
import kotlinx.android.synthetic.main.dialog_check_lesson.view.*

class LessonAdapter(var mLessonViewModel:LessonViewModel, var mcontext:Context):RecyclerView.Adapter<LessonAdapter.MyViewHolder>() {

    private lateinit var binding: CustomLessonRowBinding
    private var lessonList = emptyList<Lesson>()
    private var gridLayoutManager: GridLayoutManager? = null


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomLessonRowBinding.inflate(LayoutInflater.from(parent.context))

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = lessonList[position]



        holder.itemView.lesson_subject_text.text = currentItem.name

        val LessonList = ItemLessonCheck(currentItem.l01,currentItem.l02,currentItem.l03,currentItem.l04,currentItem.l05,currentItem.l06,currentItem.l07,currentItem.l08,currentItem.l09,currentItem.l10
                ,currentItem.l11,currentItem.l12,currentItem.l13,currentItem.l14,currentItem.l15,currentItem.l16)

        val CheckLesson_1 = arrayListOf<Int>(currentItem.l01_C1,currentItem.l02_C1,currentItem.l03_C1,currentItem.l04_C1,currentItem.l05_C1,currentItem.l06_C1,currentItem.l07_C1,currentItem.l08_C1,
                currentItem.l09_C1,currentItem.l10_C1,currentItem.l11_C1,currentItem.l12_C1,currentItem.l13_C1,currentItem.l14_C1,currentItem.l15_C1,currentItem.l16_C1)

        val CheckLesson_2 = arrayListOf<Int>(currentItem.l01_C2,currentItem.l02_C2,currentItem.l03_C2,currentItem.l04_C2,currentItem.l05_C2,currentItem.l06_C2,currentItem.l07_C2,currentItem.l08_C2,
                currentItem.l09_C2,currentItem.l10_C2,currentItem.l11_C2,currentItem.l12_C2,currentItem.l13_C2,currentItem.l14_C2,currentItem.l15_C2,currentItem.l16_C2)

        val CheckLesson_3 = arrayListOf<Int>(currentItem.l01_C3,currentItem.l02_C3,currentItem.l03_C3,currentItem.l04_C3,currentItem.l05_C3,currentItem.l06_C3,currentItem.l07_C3,currentItem.l08_C3,
                currentItem.l09_C3,currentItem.l10_C3,currentItem.l11_C3,currentItem.l12_C3,currentItem.l13_C3,currentItem.l14_C3,currentItem.l15_C3,currentItem.l16_C3)

        val CheckLesson_4 = arrayListOf<Int>(currentItem.l01_C4,currentItem.l02_C4,currentItem.l03_C4,currentItem.l04_C4,currentItem.l05_C4,currentItem.l06_C4,currentItem.l07_C4,currentItem.l08_C4,
                currentItem.l09_C4,currentItem.l10_C4,currentItem.l11_C4,currentItem.l12_C4,currentItem.l13_C4,currentItem.l14_C4,currentItem.l15_C4,currentItem.l16_C4)


        holder.itemView.set_check_lesson.setOnClickListener {

            dialogCheckLesson(LessonList,mLessonViewModel,CheckLesson_1,CheckLesson_2,CheckLesson_3,CheckLesson_4,currentItem)
        }



        val pageAdapter = SubLessonViewPagerAdapter(LessonList,CheckLesson_1,CheckLesson_2,CheckLesson_3,CheckLesson_4,currentItem)

        holder.itemView.vpsub.adapter = pageAdapter
        holder.itemView.indicate.setViewPager(holder.itemView.vpsub)



    }

    override fun getItemCount(): Int {
        return lessonList.size
    }

    fun setData(lesson: List<Lesson>){
        this.lessonList = lesson
        notifyDataSetChanged()
    }


    private fun ItemLessonCheck(string1: String,string2: String,string3: String,string4: String,string5: String,string6: String,string7: String,string8: String,
                                string9: String,string10: String,string11: String,string12: String,string13: String,string14: String,string15: String,string16: String): List<String>{
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

        if (inputCheck(string6)){
            listItem.add(string6)
        }
        if (inputCheck(string7)){
            listItem.add(string7)
        }
        if (inputCheck(string8)){
            listItem.add(string8)
        }
        if (inputCheck(string9)){
            listItem.add(string9)
        }
        if (inputCheck(string10)){
            listItem.add(string10)
        }
        if (inputCheck(string11)){
            listItem.add(string11)
        }
        if (inputCheck(string12)){
            listItem.add(string12)
        }
        if (inputCheck(string13)){
            listItem.add(string13)
        }
        if (inputCheck(string14)){
            listItem.add(string14)
        }
        if (inputCheck(string15)){
            listItem.add(string15)
        }
        if (inputCheck(string16)){
            listItem.add(string16)
        }
        return listItem
    }

    private fun inputCheck(string: String):Boolean{

        return  !(TextUtils.isEmpty(string))

    }

    private fun dialogCheckLesson(ListLesson:List<String>, lessonViewModel: LessonViewModel, CL1:ArrayList<Int>, CL2:ArrayList<Int>, CL3:ArrayList<Int>, CL4:ArrayList<Int>, CurrentLesson:Lesson){
        val checkLesson = LayoutInflater.from(mcontext).inflate(R.layout.dialog_check_lesson,null)

        var Adapter = CheckLessonListAdapter(ListLesson,lessonViewModel,CL1,CL2,CL3,CL4,CurrentLesson)

        val mBuilder = AlertDialog.Builder(mcontext)
                .setView(checkLesson)






        checkLesson.check_list_lesson.adapter = Adapter
        checkLesson.check_list_lesson.setItemViewCacheSize(100)

        gridLayoutManager = GridLayoutManager(mcontext,1, LinearLayoutManager.VERTICAL,false)

        checkLesson.check_list_lesson.layoutManager =  gridLayoutManager

        checkLesson.lesson_title.text = CurrentLesson.name


        println("size${checkLesson.check_list_lesson.size}")





        val mAlert = mBuilder.show()

        val alertDialog = mBuilder.create()



        checkLesson.save_lesson_state.setOnClickListener {
            mAlert.dismiss()
        }
        


    }

}