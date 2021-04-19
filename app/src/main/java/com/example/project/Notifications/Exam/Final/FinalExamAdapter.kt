package com.example.project.Notifications.Exam.Final

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DataBase.model.Subject
import com.example.project.Notifications.Exam.Mid.MidExamAdapter
import com.example.project.R
import com.example.project.databinding.CustomExamRowBinding
import kotlinx.android.synthetic.main.custom_exam_row.view.*
import kotlinx.android.synthetic.main.dialog_building_view.view.*

class FinalExamAdapter(context: Context):RecyclerView.Adapter<FinalExamAdapter.MyViewHolder>() {

    private var subjectList = emptyList<Subject>()
    private lateinit var binding: CustomExamRowBinding
    private var mcontext:Context

    init {
        mcontext = context
    }

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomExamRowBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = subjectList[position]
        holder.itemView.sid_text.text = currentItem.sid
        holder.itemView.sname_text.text = currentItem.Name
        holder.itemView.date_textview.text = currentItem.final_date
        holder.itemView.sb_text.text = currentItem.final_building
        holder.itemView.sr_text.text = currentItem.final_room
        holder.itemView.time_textview.text = currentItem.final_begin_time  + "-" + currentItem.final_end_time

        holder.itemView.midterm_row.animation = AnimationUtils.loadAnimation(holder.itemView.midterm_row.context,R.anim.item_animation_waterfall)




        holder.itemView.building_picture_button.setOnClickListener {
            buildingView(holder.itemView.sb_text.text.toString())
        }

        holder.itemView.sid_text.setOnClickListener {
            holder.itemView.sid_text.isInvisible = true
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

            "82" -> {ss = "geo:0,0?q=13.82170596381349,100.513040349729(Google+Bangkok)"
                println("82")}

            "83" -> {ss = "geo:0,0?q=13.822032743778463,100.5133276268752(Google+Bangkok)"
                println("83")}

            "84" -> {ss = "geo:0,0?q=13.82173943885497,100.51380368615145(Google+Bangkok)"
                println("84")}

            "85" -> {ss = "geo:0,0?q=13.821382371493481,100.51379876140038(Google+Bangkok)"
                println("85")}

            "86" -> {ss = "geo:0,0?q=13.822451248310156,100.51326502359224(Google+Bangkok)"
                println("86")}

            "88" -> {ss = "geo:0,0?q=13.822563091586796,100.5130854227556(Google+Bangkok)"
                println("88")}

            else -> {ss = "geo:0,0?q=13.82210271562129,100.51270047443425(Google+Bangkok)"
                println("else")}



        }

        val uri = Uri.parse(ss)
        val intent = Intent(Intent.ACTION_VIEW,uri)
        intent.setPackage("com.google.android.apps.maps")
        mcontext.startActivity(Intent.createChooser(intent,"View map with"))

    }

    private fun buildingView(Building:String){

        val ViewBuilding = LayoutInflater.from(mcontext).inflate(R.layout.dialog_building_view,null)
        val mBuilder = AlertDialog.Builder(mcontext)
                .setView(ViewBuilding)

        val mAlert = mBuilder.show()

        if (Building == "81"){
            ViewBuilding.building_image.setImageResource(R.drawable.b81)
        }
        else if (Building == "83"){
            ViewBuilding.building_image.setImageResource(R.drawable.b83)
        }
        else if (Building == "84"){
            ViewBuilding.building_image.setImageResource(R.drawable.b84)
        }
        else if (Building == "86"){
            ViewBuilding.building_image.setImageResource(R.drawable.b86)
        }
        else if (Building == "88"){
            ViewBuilding.building_image.setImageResource(R.drawable.b88)
        }
        else if (Building == "89"){
            ViewBuilding.building_image.setImageResource(R.drawable.b89)
        }

        ViewBuilding.navigate_button.setOnClickListener {
            findLocation(Building)
        }


        ViewBuilding.exit_button.setOnClickListener {
            mAlert.dismiss()
        }


    }
}