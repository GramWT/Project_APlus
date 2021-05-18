package com.example.project.Notifications.Event

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.Event
import com.example.project.DataBase.model.EventCalendar
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.EventViewModel
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_reminder_notification_update.*
import kotlinx.android.synthetic.main.fragment_reminder_notification_update.view.*
import java.text.SimpleDateFormat
import java.util.*


class ReminderNotificationUpdate : Fragment() {

    private val args by navArgs<ReminderNotificationViewArgs>()
    private lateinit var mEventViewModel: EventViewModel
    private lateinit var mEventCalendarViewModel: EventCalendarViewModel
    private lateinit var mAlarmService: AlarmService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =
            inflater.inflate(R.layout.fragment_reminder_notification_update, container, false)

        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        mEventCalendarViewModel = ViewModelProvider(this).get(EventCalendarViewModel::class.java)
        mAlarmService = AlarmService(requireContext())

        val currentItem = args.Reminder

        view.title_remind_update.setText(currentItem.title)
        view.date_begin_remind_update.text = currentItem.date_begin
        view.time_begin_remind_update.text = currentItem.time_begin
        view.location_remind_update.setText(currentItem.location)
        view.description_remind_update.setText(currentItem.description)
        view.state_remind_update.text = currentItem.state

        setState(currentItem.state, view.state_image_remind)

        view.state_remind_update.setOnClickListener {
            setStateView(view.state_remind_update, state_image_remind)
        }

        view.date_begin_remind_update.setOnClickListener {
            getDate(view.date_begin_remind_update)
        }

        view.time_begin_remind_update.setOnClickListener {
            getTime(view.time_begin_remind_update)
        }

        view.cancel_remind_button.setOnClickListener {
            val action =
                ReminderNotificationUpdateDirections.actionReminderNotificationUpdateToReminderNotificationView(
                    currentItem
                )
            findNavController().navigate(action)
        }

        view.save_remind_button.setOnClickListener {
            updateItem()
            val title = title_remind_update.text.toString()
            val beginDate = date_begin_remind_update.text.toString()
            val beginTime = time_begin_remind_update.text.toString()
            val state = state_remind_update.text.toString()
            val description = description_remind_update.text.toString()
            val location = location_remind_update.text.toString()
            val reminder = Event(
                currentItem.id, title,
                beginDate,
                "-",
                beginTime,
                "-",
                state,
                description,
                "-",
                "-",
                "-",
                "-",
                "-",
                "-",
                "-",
                "-",
                "-",
                "-",
                location, 2
            )


            val action =
                ReminderNotificationUpdateDirections.actionReminderNotificationUpdateToReminderNotificationView(
                    reminder
                )
            Toast.makeText(requireContext(), "Update Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(action)
        }


        return view
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateItem() {
        val title = title_remind_update.text.toString()
        val beginDate = date_begin_remind_update.text.toString()
        val beginTime = time_begin_remind_update.text.toString()
        val state = state_remind_update.text.toString()
        val description = description_remind_update.text.toString()
        val location = location_remind_update.text.toString()
        val reminder = Event(
            args.Reminder.id, title,
            beginDate,
            "-",
            beginTime,
            "-",
            state,
            description,
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            "-",
            location, 2
        )

        val reminderEvent = EventCalendar(
            args.Reminder.id,
            3,
            beginDate.substring(0, 2).toInt(),
            beginDate.substring(3, 5).toInt() - 1,
            beginDate.substring(6, 10).toInt(),
            title
        )

        mEventViewModel.updateEvent(reminder)
        mEventCalendarViewModel.updateEventCalendar(reminderEvent)

        val duringReminder =
            "${date_begin_remind_update.text.toString()} ${time_begin_remind_update.text.toString()}:00"
        if (date_begin_remind_update.text.toString() != "" && time_begin_remind_update.text.toString() != "") {
            val dt1 = "${date_begin_remind_update.text} ${time_begin_remind_update.text}:00"
            val rId1 = "1${args.Reminder.id}".toInt()

            setAlarm(rId1, title, state, duringReminder, dt1)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setAlarm(
        requestCode: Int,
        title: String,
        priority: String,
        duringReminder: String,
        nowTime: String
    ) {
        mAlarmService.setReminderAlarm(requestCode, title, priority, duringReminder, nowTime)
    }


    private fun setState(state: String, im: ImageView) {
        when (state) {
            "High" -> im.setImageResource(R.drawable.status_event_red)
            "Normal" -> im.setImageResource(R.drawable.status_event_green)
            "Low" -> im.setImageResource(R.drawable.status_event_blue)
        }
    }

    private fun setStateView(tv: TextView, im: ImageView) {

        val ListState = arrayOf("High", "Normal", "Low")

        val BBuilder = AlertDialog.Builder(requireContext())

        BBuilder.setTitle("Choose State")

        BBuilder.setSingleChoiceItems(ListState, -1) { dialog, i ->

            tv.setText(ListState[i])
            dialog.dismiss()
            when (ListState[i]) {
                "High" -> im.setImageResource(R.drawable.status_event_red)
                "Normal" -> im.setImageResource(R.drawable.status_event_green)
                "Low" -> im.setImageResource(R.drawable.status_event_blue)
            }
        }

        BBuilder.show()
    }

    private fun getTime(tv: TextView) {
        val cal = Calendar.getInstance()
        val timeSet = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            tv.text = SimpleDateFormat("HH:mm").format(cal.time).toString()
        }
        TimePickerDialog(
            requireContext(),
            AlertDialog.THEME_HOLO_DARK,
            timeSet,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(
                Calendar.MINUTE
            ),
            true
        ).show()
    }

    private fun getDate(tv: TextView) {
        val cal = Calendar.getInstance()
        val dpd = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.YEAR, year)



            tv.text = SimpleDateFormat("dd/MM/yyyy").format(cal.time).toString()


        }

        DatePickerDialog(
            requireContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK, dpd, cal.get(
                Calendar.YEAR
            ), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)
        ).show()

    }


}