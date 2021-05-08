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
import kotlinx.android.synthetic.main.fragment_reminder_notification_view.view.*

class ReminderNotificationView : Fragment() {

    private val args by navArgs<ReminderNotificationViewArgs>()
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reminder_notification_view, container, false)
        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        mEventCalendar = ViewModelProvider(this).get(EventCalendarViewModel::class.java)
        mAlarmService = AlarmService(requireContext())

        val currentItem = args.Reminder

        view.title_reminder_view.text = args.Reminder.title
        view.date_begin_reminder_view.text = args.Reminder.date_begin
        view.time_begin_reminder_view.text = args.Reminder.time_begin
        view.description_reminder_view.text = args.Reminder.description
        view.location_reminder_view.text = args.Reminder.location

        if (view.location_reminder_view.text == "") {
            view.location_reminder_view.visibility = View.GONE
            view.imageViewLocation.visibility = View.GONE
        }

        setState(currentItem.state, view.state_reminder_view)

        view.back_reminder_view.setOnClickListener {
            val action = ReminderNotificationViewDirections.actionReminderNotificationViewToEventNotification()
            view.findNavController().navigate(action)
            val a = activity as MainActivity
            a.bottom_navigation.visibility = View.VISIBLE
        }

        view.setting_reminder_view.setOnClickListener {
            val action = ReminderNotificationViewDirections.actionReminderNotificationViewToReminderNotificationUpdate(currentItem)
            view.findNavController().navigate(action)
        }

        view.delete_reminder_view.setOnClickListener {
            deleteReminder()
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

    private fun deleteReminder() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->
            println(args.Reminder.id)
            mEventViewModel.deleteEvent(args.Reminder)
            mEventCalendar.deleteById(args.Reminder.id)
            cancelAlarm(args.Reminder.id)
            Toast.makeText(requireContext(), "Successfully deleted ", Toast.LENGTH_SHORT).show()
            val action = ReminderNotificationViewDirections.actionReminderNotificationViewToEventNotification()
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
        val rId1 = "1${rq}".toInt()
        CancelAlarm(rId1)
    }

    private fun CancelAlarm(rq: Int) {
        mAlarmService.cancelOnceAlarm(rq)
    }


}