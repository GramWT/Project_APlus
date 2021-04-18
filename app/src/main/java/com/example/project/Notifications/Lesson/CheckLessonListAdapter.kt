package com.example.project.Notifications.Lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DataBase.model.Lesson
import com.example.project.DataBase.viewmodel.LessonViewModel
import com.example.project.databinding.LessonCheckRowBinding
import kotlinx.android.synthetic.main.lesson_check_row.view.*

class CheckLessonListAdapter(private val ListLesson:List<String>, private val LVM: LessonViewModel, private var CL1:ArrayList<Int>, private var CL2:ArrayList<Int>,
        private var CL3:ArrayList<Int>,private val CL4:ArrayList<Int>, private val currentLesson:Lesson):RecyclerView.Adapter<CheckLessonListAdapter.MyViewHolder>() {

    private lateinit var binding:LessonCheckRowBinding




    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckLessonListAdapter.MyViewHolder {
        binding = LessonCheckRowBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CheckLessonListAdapter.MyViewHolder, position: Int) {
        holder.itemView.text_check.text = ListLesson[position]

        if (CL1[position] == 0){
            holder.itemView.check1.isChecked = false
        }
        else if (CL1[position] == 1){
            holder.itemView.check1.isChecked = true
        }

        if (CL2[position] == 0){
            holder.itemView.check2.isChecked = false
        }
        else if (CL2[position] == 1){
            holder.itemView.check2.isChecked = true
        }

        if (CL3[position] == 0){
            holder.itemView.check3.isChecked = false
        }
        else if (CL3[position] == 1){
            holder.itemView.check3.isChecked = true
        }

        if (CL4[position] == 0){
            holder.itemView.check4.isChecked = false
        }
        else if (CL4[position] == 1){
            holder.itemView.check4.isChecked = true
        }


        holder.itemView.check1.setOnCheckedChangeListener { buttonView, isChecked ->
            var Check1 = CL1[position]

            if (isChecked){
                Check1 = 1

                CL1.set(position,Check1)


            }
            else if (!isChecked){
                Check1 = 0
                CL1.set(position,Check1)
            }

            val lesson = Lesson(currentLesson.id,currentLesson.name,currentLesson.l01,currentLesson.l02,currentLesson.l03,currentLesson.l04,currentLesson.l05,currentLesson.l06,currentLesson.l07,currentLesson.l08,currentLesson.l09,currentLesson.l10,currentLesson.l11,currentLesson.l12,currentLesson.l13,currentLesson.l14,currentLesson.l15,currentLesson.l16,
                    CL1[0],CL1[1],CL1[2],CL1[3],CL1[4],CL1[5],CL1[6],CL1[7],CL1[8],CL1[9],CL1[10],CL1[11],CL1[12],CL1[13],CL1[14],CL1[15],
                    CL2[0],CL2[1],CL2[2],CL2[3],CL2[4],CL2[5],CL2[6],CL2[7],CL2[8],CL2[9],CL2[10],CL2[11],CL2[12],CL2[13],CL2[14],CL2[15],
                    CL3[0],CL3[1],CL3[2],CL3[3],CL3[4],CL3[5],CL3[6],CL3[7],CL3[8],CL3[9],CL3[10],CL3[11],CL3[12],CL3[13],CL3[14],CL3[15],
                    CL4[0],CL4[1],CL4[2],CL4[3],CL4[4],CL4[5],CL4[6],CL4[7],CL4[8],CL4[9],CL4[10],CL4[11],CL4[12],CL4[13],CL4[14],CL4[15])
            LVM.updateLesson(lesson)
        }

        holder.itemView.check2.setOnCheckedChangeListener { buttonView, isChecked ->
            var Check2 = CL2[position]

            if (isChecked){
                Check2 = 1
                CL2.set(position,Check2)
            }
            else if (!isChecked){
                Check2 = 0
                CL2.set(position,Check2)
            }

            val lesson = Lesson(currentLesson.id,currentLesson.name,currentLesson.l01,currentLesson.l02,currentLesson.l03,currentLesson.l04,currentLesson.l05,currentLesson.l06,currentLesson.l07,currentLesson.l08,currentLesson.l09,currentLesson.l10,currentLesson.l11,currentLesson.l12,currentLesson.l13,currentLesson.l14,currentLesson.l15,currentLesson.l16,
                    CL1[0],CL1[1],CL1[2],CL1[3],CL1[4],CL1[5],CL1[6],CL1[7],CL1[8],CL1[9],CL1[10],CL1[11],CL1[12],CL1[13],CL1[14],CL1[15],
                    CL2[0],CL2[1],CL2[2],CL2[3],CL2[4],CL2[5],CL2[6],CL2[7],CL2[8],CL2[9],CL2[10],CL2[11],CL2[12],CL2[13],CL2[14],CL2[15],
                    CL3[0],CL3[1],CL3[2],CL3[3],CL3[4],CL3[5],CL3[6],CL3[7],CL3[8],CL3[9],CL3[10],CL3[11],CL3[12],CL3[13],CL3[14],CL3[15],
                    CL4[0],CL4[1],CL4[2],CL4[3],CL4[4],CL4[5],CL4[6],CL4[7],CL4[8],CL4[9],CL4[10],CL4[11],CL4[12],CL4[13],CL4[14],CL4[15])
            LVM.updateLesson(lesson)

        }

        holder.itemView.check3.setOnCheckedChangeListener { buttonView, isChecked ->
            var Check3 = CL3[position]

            if (isChecked){
                Check3 = 1
                CL3.set(position,Check3)
            }
            else if (!isChecked){
                Check3 = 0
                CL3.set(position,Check3)
            }

            val lesson = Lesson(currentLesson.id,currentLesson.name,currentLesson.l01,currentLesson.l02,currentLesson.l03,currentLesson.l04,currentLesson.l05,currentLesson.l06,currentLesson.l07,currentLesson.l08,currentLesson.l09,currentLesson.l10,currentLesson.l11,currentLesson.l12,currentLesson.l13,currentLesson.l14,currentLesson.l15,currentLesson.l16,
                    CL1[0],CL1[1],CL1[2],CL1[3],CL1[4],CL1[5],CL1[6],CL1[7],CL1[8],CL1[9],CL1[10],CL1[11],CL1[12],CL1[13],CL1[14],CL1[15],
                    CL2[0],CL2[1],CL2[2],CL2[3],CL2[4],CL2[5],CL2[6],CL2[7],CL2[8],CL2[9],CL2[10],CL2[11],CL2[12],CL2[13],CL2[14],CL2[15],
                    CL3[0],CL3[1],CL3[2],CL3[3],CL3[4],CL3[5],CL3[6],CL3[7],CL3[8],CL3[9],CL3[10],CL3[11],CL3[12],CL3[13],CL3[14],CL3[15],
                    CL4[0],CL4[1],CL4[2],CL4[3],CL4[4],CL4[5],CL4[6],CL4[7],CL4[8],CL4[9],CL4[10],CL4[11],CL4[12],CL4[13],CL4[14],CL4[15])
            LVM.updateLesson(lesson)
        }

        holder.itemView.check4.setOnCheckedChangeListener { buttonView, isChecked ->
            var Check4 = CL4[position]

            if (isChecked){
                Check4 = 1
                CL4.set(position,Check4)
            }
            else if (!isChecked){
                Check4 = 0
                CL4.set(position,Check4)
            }

            val lesson = Lesson(currentLesson.id,currentLesson.name,currentLesson.l01,currentLesson.l02,currentLesson.l03,currentLesson.l04,currentLesson.l05,currentLesson.l06,currentLesson.l07,currentLesson.l08,currentLesson.l09,currentLesson.l10,currentLesson.l11,currentLesson.l12,currentLesson.l13,currentLesson.l14,currentLesson.l15,currentLesson.l16,
                    CL1[0],CL1[1],CL1[2],CL1[3],CL1[4],CL1[5],CL1[6],CL1[7],CL1[8],CL1[9],CL1[10],CL1[11],CL1[12],CL1[13],CL1[14],CL1[15],
                    CL2[0],CL2[1],CL2[2],CL2[3],CL2[4],CL2[5],CL2[6],CL2[7],CL2[8],CL2[9],CL2[10],CL2[11],CL2[12],CL2[13],CL2[14],CL2[15],
                    CL3[0],CL3[1],CL3[2],CL3[3],CL3[4],CL3[5],CL3[6],CL3[7],CL3[8],CL3[9],CL3[10],CL3[11],CL3[12],CL3[13],CL3[14],CL3[15],
                    CL4[0],CL4[1],CL4[2],CL4[3],CL4[4],CL4[5],CL4[6],CL4[7],CL4[8],CL4[9],CL4[10],CL4[11],CL4[12],CL4[13],CL4[14],CL4[15])
            LVM.updateLesson(lesson)
        }

    }

    override fun getItemCount(): Int {
        return ListLesson.size
    }
}