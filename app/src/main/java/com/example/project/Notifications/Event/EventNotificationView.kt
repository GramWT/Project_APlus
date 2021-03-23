package com.example.project.Notifications.Event

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.viewmodel.EventViewModel
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.MainActivity

import com.example.project.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_building_select.view.*
import kotlinx.android.synthetic.main.fragment_event_notification_view.view.*


class EventNotificationView : Fragment() {
    private val args by navArgs<EventNotificationViewArgs>()
    private lateinit var mEventViewModel: EventViewModel


    override fun onResume() {

        super.onResume()
        val a = activity as MainActivity
        a.bottom_navigation.visibility = View.GONE
    }






    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_event_notification_view, container, false)
        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        val currentItem = args.event

        view.title_event_view.setText(args.event.title)
        view.date_begin_event_view.setText(args.event.date_begin)
        view.date_end_event_view.setText(args.event.date_end)
        view.time_begin_event_view.setText(args.event.time_begin)
        view.time_end_event_view.setText(args.event.time_end)
        view.description_event_view.setText(args.event.description)
        view.location_event_view.setText(args.event.location)

        view.time_1.setText(args.event.time_notification_1)
        view.time_2.setText(args.event.time_notification_2)
        view.time_3.setText(args.event.time_notification_3)
        view.time_4.setText(args.event.time_notification_4)
        view.time_5.setText(args.event.time_notification_5)

        if (view.location_event_view.text == ""){
            view.location_event_view.visibility = View.GONE
            view.imageView4.visibility = View.GONE
        }

        if (view.time_1.text == "null"){
            view.time_view_1.visibility = View.GONE
        }

        if (view.time_2.text == "null"){
            view.time_view_2.visibility = View.GONE
        }

        if (view.time_3.text == "null"){
            view.time_view_3.visibility = View.GONE
        }

        if (view.time_4.text == "null"){
            view.time_view_4.visibility = View.GONE
        }

        if (view.time_5.text == "null"){
            view.time_view_5.visibility = View.GONE
        }
        setState(args.event.state,view.state_event_view)

        view.back_event_view.setOnClickListener {
            val action = EventNotificationViewDirections.actionEventNotificationViewToEventNotification()
            view.findNavController().navigate(action)
            val a = activity as MainActivity
            a.bottom_navigation.visibility = View.VISIBLE
        }

        view.delete_event_view.setOnClickListener {
            deleteEvent()
        }

        view.setting_event_view.setOnClickListener {
            val action = EventNotificationViewDirections.actionEventNotificationViewToEventNotificationUpdate(args.event)
            view.findNavController().navigate(action)
        }
        return view
    }

    private fun setState(state: String, im: ImageView){
        when(state){
            "High" -> im.setImageResource(R.drawable.status_event_red)
            "Normal" -> im.setImageResource(R.drawable.status_event_green)
            "Low" -> im.setImageResource(R.drawable.status_event_blue)
        }
    }

    private fun deleteEvent(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){ _, _ ->
            mEventViewModel.deleteEvent(args.event)
            Toast.makeText(requireContext(),"Successfully deleted ", Toast.LENGTH_SHORT).show()
            val action = EventNotificationViewDirections.actionEventNotificationViewToEventNotification()
            findNavController().navigate(action)

        }
        builder.setNegativeButton("No"){ _ ,_ ->}
        builder.setTitle("Delete ?")
        builder.setMessage("Are you sure you want to delete?")
        builder.show()
    }


}