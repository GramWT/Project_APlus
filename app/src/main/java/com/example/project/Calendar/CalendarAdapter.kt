package com.example.project.Calendar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DataBase.model.EventCalendar
import com.example.project.R
import com.example.project.databinding.CustomListEventCalendarBinding
import kotlinx.android.synthetic.main.custom_list_event_calendar.view.*

class CalendarAdapter : RecyclerView.Adapter<CalendarAdapter.MyViewHolder>() {

    private var eventList = emptyList<EventCalendar>()
    private lateinit var binding: CustomListEventCalendarBinding

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomListEventCalendarBinding.inflate(LayoutInflater.from(parent.context))

        return MyViewHolder(binding.root)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = eventList[position]

        holder.itemView.subject_title.text = currentItem.subject
        if (currentItem.type == 1) {
            holder.itemView.state_title.text = "Exam"
        } else if (currentItem.type == 2) {
            holder.itemView.state_title.text = "Event"
        } else if (currentItem.type == 3) {
            holder.itemView.state_title.text = "Reminder"
        }

    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    fun setData(event: List<EventCalendar>) {
        this.eventList = event
        notifyDataSetChanged()
    }
}