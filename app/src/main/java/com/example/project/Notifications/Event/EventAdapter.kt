package com.example.project.Notifications.Event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DataBase.model.Event
import com.example.project.R
import com.example.project.databinding.CustomEventRowBinding
import kotlinx.android.synthetic.main.custom_event_row.view.*

class EventAdapter : RecyclerView.Adapter<EventAdapter.MyViewHolder>() {

    private var eventList = emptyList<Event>()
    private lateinit var binding: CustomEventRowBinding

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = CustomEventRowBinding.inflate(LayoutInflater.from(parent.context))

        return MyViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = eventList[position]


        holder.setIsRecyclable(false)
        holder.itemView.event_row.animation = AnimationUtils.loadAnimation(holder.itemView.event_row.context, R.anim.item_slide_right)

        holder.itemView.circle_state.animation = AnimationUtils.loadAnimation(holder.itemView.circle_state.context, R.anim.item_slide_left)


        holder.itemView.title_event.text = currentItem.title
        holder.itemView.date_begin_event.text = currentItem.date_begin
        holder.itemView.date_end_event.text = currentItem.date_end
        holder.itemView.time_begin_event.text = currentItem.time_begin
        holder.itemView.time_end_event.text = currentItem.time_end


        if (currentItem.type == 1) {
            holder.itemView.type_event.text = "Event"
        } else if (currentItem.type == 2) {
            holder.itemView.type_event.text = "Reminder"
            holder.itemView.date_end_event.text = ""
            holder.itemView.time_end_event.text = ""
        }



        holder.itemView.event_row.setOnClickListener {

            if (currentItem.type == 1) {
                val action = EventNotificationDirections.actionEventNotificationToEventNotificationView(currentItem)
                holder.itemView.findNavController().navigate(action)
            } else if (currentItem.type == 2) {
                val action = EventNotificationDirections.actionEventNotificationToReminderNotificationView(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
        }

        setState(currentItem.state, holder.itemView.circle_state)


    }

    override fun getItemCount(): Int {
        return eventList.size
    }


    fun setData(event: List<Event>) {
        this.eventList = event
        notifyDataSetChanged()
    }

    private fun setState(state: String, im: ImageView) {
        when (state) {
            "High" -> im.setImageResource(R.drawable.status_event_red)
            "Normal" -> im.setImageResource(R.drawable.status_event_green)
            "Low" -> im.setImageResource(R.drawable.status_event_blue)
        }
    }
}