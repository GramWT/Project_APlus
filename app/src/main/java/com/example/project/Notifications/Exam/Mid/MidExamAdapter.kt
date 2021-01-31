package com.example.project.Notifications.Exam.Mid

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DataBase.model.Subject
import com.example.project.Notifications.Exam.ExamNotification
import com.example.project.databinding.CustomExamRowBinding
import kotlinx.android.synthetic.main.custom_exam_row.view.*
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_setting.view.*
import kotlinx.android.synthetic.main.fragment_subject_tab.view.*

class MidExamAdapter(context: Context):RecyclerView.Adapter<MidExamAdapter.MyViewHolder>() {

    private var subjectList = emptyList<Subject>()
    private lateinit var binding: CustomExamRowBinding
    private var mcontext:Context

    init {
        mcontext = context
    }

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomExamRowBinding.inflate(LayoutInflater.from(parent.context))

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = subjectList[position]
        holder.itemView.sid_text.text = currentItem.sid
        holder.itemView.sname_text.text = currentItem.Name
        holder.itemView.date_textview.text = currentItem.mid_date
        holder.itemView.sb_text.text = currentItem.mid_building
        holder.itemView.sr_text.text = currentItem.mid_room
        holder.itemView.time_textview.text = currentItem.mid_begin_time  + "-" + currentItem.mid_end_time



        holder.itemView.building_picture_button.setOnClickListener {
            findLocation(holder.itemView.sb_text.text.toString())
            println(holder.itemView.sb_text.text.toString())
        }

        holder.itemView.update_button.setOnClickListener {
            val action = MidExamNotificationDirections.actionMidExamNotificationToMidExamUpdate(currentItem)
            holder.itemView.findNavController().navigate(action)


        }

    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    fun setData(subject: List<Subject>){
        this.subjectList = subject
        notifyDataSetChanged()
    }
    private fun findLocation(Building:String){

        var ss:String

        when(Building){
            "89" -> {ss = "geo:0,0?q=13.82210271562129,100.51270047443425(Google+Bangkok)"
                    println("89")}
            "81" -> {ss = "geo:0,0?q=13.821240667570667,100.5136312052945(Google+Bangkok)"
                    println("81")}
            else -> {ss = "geo:0,0?q=13.82210271562129,100.51270047443425(Google+Bangkok)"
                    println("else")}
        }

        val uri = Uri.parse(ss)
        val intent = Intent(Intent.ACTION_VIEW,uri)
        intent.setPackage("com.google.android.apps.maps")
        mcontext.startActivity(Intent.createChooser(intent,"View map with"))

    }



}