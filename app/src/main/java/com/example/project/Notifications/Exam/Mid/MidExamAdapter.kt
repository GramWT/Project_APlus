package com.example.project.Notifications.Exam.Mid

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
import com.example.project.R
import com.example.project.databinding.CustomExamRowBinding
import kotlinx.android.synthetic.main.custom_exam_row.view.*
import kotlinx.android.synthetic.main.dialog_building_view.view.*

class MidExamAdapter(context: Context) : RecyclerView.Adapter<MidExamAdapter.MyViewHolder>() {
    private var subjectList = emptyList<Subject>()
    private lateinit var binding: CustomExamRowBinding
    private val mcontext: Context

    init {
        mcontext = context
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

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
        holder.itemView.time_textview.text =
            currentItem.mid_begin_time + "-" + currentItem.mid_end_time

        holder.itemView.midterm_row.animation = AnimationUtils.loadAnimation(
            holder.itemView.midterm_row.context,
            R.anim.item_animation_waterfall
        )


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

    fun setData(subject: List<Subject>) {
        this.subjectList = subject
        notifyDataSetChanged()
    }

    private fun findLocation(Building: String) {
        val map: String
        when (Building) {
            "89" -> {
                map = "geo:0,0?q=13.82210271562129,100.51270047443425(Google+Bangkok)"
            }

            "81" -> {
                map = "geo:0,0?q=13.821240667570667,100.5136312052945(Google+Bangkok)"
            }

            "82" -> {
                map = "geo:0,0?q=13.82170596381349,100.513040349729(Google+Bangkok)"
            }

            "83" -> {
                map = "geo:0,0?q=13.822032743778463,100.5133276268752(Google+Bangkok)"
            }

            "84" -> {
                map = "geo:0,0?q=13.82173943885497,100.51380368615145(Google+Bangkok)"
            }

            "85" -> {
                map = "geo:0,0?q=13.821382371493481,100.51379876140038(Google+Bangkok)"
            }

            "86" -> {
                map = "geo:0,0?q=13.822451248310156,100.51326502359224(Google+Bangkok)"
            }

            "88" -> {
                map = "geo:0,0?q=13.822563091586796,100.5130854227556(Google+Bangkok)"
            }

            else -> {
                map = "geo:0,0?q=13.82210271562129,100.51270047443425(Google+Bangkok)"
            }
        }
        val uri = Uri.parse(map)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")
        mcontext.startActivity(Intent.createChooser(intent, "View map with"))
    }

    private fun buildingView(Building: String) {
        val viewBuilding =
            LayoutInflater.from(mcontext).inflate(R.layout.dialog_building_view, null)
        val mBuilder = AlertDialog.Builder(mcontext)
            .setView(viewBuilding)

        val mAlert = mBuilder.show()

        if (Building == "81") {
            viewBuilding.building_image.setImageResource(R.drawable.b81)
        } else if (Building == "83") {
            viewBuilding.building_image.setImageResource(R.drawable.b83)
        } else if (Building == "84") {
            viewBuilding.building_image.setImageResource(R.drawable.b84)
        } else if (Building == "86") {
            viewBuilding.building_image.setImageResource(R.drawable.b86)
        } else if (Building == "88") {
            viewBuilding.building_image.setImageResource(R.drawable.b88)
        } else if (Building == "89") {
            viewBuilding.building_image.setImageResource(R.drawable.b89)
        } else {
            viewBuilding.building_image.setImageResource(R.drawable.nopicture)
        }

        viewBuilding.navigate_button.setOnClickListener {
            findLocation(Building)
        }

        viewBuilding.exit_button.setOnClickListener {
            mAlert.dismiss()
        }
    }


}