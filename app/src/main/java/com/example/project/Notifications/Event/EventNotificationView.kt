package com.example.project.Notifications.Event

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.EventViewModel
import com.example.project.MainActivity
import com.example.project.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_event_notification_view.*
import kotlinx.android.synthetic.main.fragment_event_notification_view.view.*


class EventNotificationView : Fragment() {
    private val args by navArgs<EventNotificationViewArgs>()
    private lateinit var mEventViewModel: EventViewModel
    private lateinit var mEventCalendar: EventCalendarViewModel
    private lateinit var mAlarmService: AlarmService


    override fun onResume() {

        super.onResume()
        val a = activity as MainActivity
        a.bottom_navigation.visibility = View.GONE
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_event_notification_view, container, false)
        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        mEventCalendar = ViewModelProvider(this).get(EventCalendarViewModel::class.java)
        mAlarmService = AlarmService(requireContext())

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
        view.date_1.setText(args.event.date_notification_1)
        view.date_2.setText(args.event.date_notification_2)
        view.date_3.setText(args.event.date_notification_3)
        view.date_4.setText(args.event.date_notification_4)
        view.date_5.setText(args.event.date_notification_5)

        if (view.location_event_view.text == "") {
            view.location_event_view.visibility = View.GONE
            view.imageView4.visibility = View.GONE
        }

        if (view.time_1.text == "") {
            view.time_view_1.visibility = View.GONE
        }

        if (view.time_2.text == "") {
            view.time_view_2.visibility = View.GONE
        }

        if (view.time_3.text == "") {
            view.time_view_3.visibility = View.GONE
        }

        if (view.time_4.text == "") {
            view.time_view_4.visibility = View.GONE
        }

        if (view.time_5.text == "") {
            view.time_view_5.visibility = View.GONE
        }
        setState(args.event.state, view.state_event_view)

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

    private fun setState(state: String, im: ImageView) {
        when (state) {
            "High" -> im.setImageResource(R.drawable.status_event_red)
            "Normal" -> im.setImageResource(R.drawable.status_event_green)
            "Low" -> im.setImageResource(R.drawable.status_event_blue)
        }
    }

    private fun deleteEvent() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            println(args.event.id)
            mEventViewModel.deleteEvent(args.event)
            mEventCalendar.deleteById(args.event.id)
            cancelAlarm(args.event.id)
            Toast.makeText(requireContext(), "Successfully deleted ", Toast.LENGTH_SHORT).show()
            val action = EventNotificationViewDirections.actionEventNotificationViewToEventNotification()
            findNavController().navigate(action)
            val a = activity as MainActivity
            a.bottom_navigation.visibility = View.VISIBLE

        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ?")
        builder.setMessage("Are you sure you want to delete?")
        builder.show()
    }

    private fun cancelAlarm(rq: Int) {
        if (time_view_1.visibility == View.VISIBLE) {
            val rId1 = "1${rq}".toInt()
            CancelAlarm(rId1)
        }
        if (time_view_2.visibility == View.VISIBLE) {
            val rId2 = "2${rq}".toInt()
            CancelAlarm(rId2)
        }
        if (time_view_3.visibility == View.VISIBLE) {
            val rId3 = "3${rq}".toInt()
            CancelAlarm(rId3)
        }
        if (time_view_4.visibility == View.VISIBLE) {
            val rId4 = "4${rq}".toInt()
            CancelAlarm(rId4)
        }
        if (time_view_5.visibility == View.VISIBLE) {
            val rId5 = "5${rq}".toInt()
            CancelAlarm(rId5)
        }
    }

    private fun CancelAlarm(rq: Int) {
        mAlarmService.cancelOnceAlarm(rq)
    }


}