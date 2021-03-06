package com.example.project.Notifications.Event

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
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
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.Event
import com.example.project.DataBase.model.EventCalendar
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.EventViewModel
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_reminder_notification_add.*
import kotlinx.android.synthetic.main.fragment_reminder_notification_add.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class ReminderNotificationAdd : Fragment() {

    private lateinit var mEventViewModel: EventViewModel
    private lateinit var mEventCalendar: EventCalendarViewModel
    private lateinit var mAlarmService: AlarmService
    private lateinit var mCalendar: List<EventCalendar>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_reminder_notification_add, container, false)

        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        mEventCalendar = ViewModelProvider(this).get(EventCalendarViewModel::class.java)

        mAlarmService = AlarmService(requireContext())

        view.cancel_remind_button.setOnClickListener {
            val action =
                ReminderNotificationAddDirections.actionReminderNotificationAddToEventNotification()
            findNavController().navigate(action)
        }

        view.date_begin_remind_add.setText(
            DateTimeFormatter.ofPattern("dd/MM/yyyy").format(roundTime())
        )
        view.time_begin_remind_add.setText(DateTimeFormatter.ofPattern("HH:mm").format(roundTime()))
        view.state_remind_add.setOnClickListener {
            setState(view.state_remind_add, view.state_image_remind)
        }

        view.date_begin_remind_add.setOnClickListener {
            getDate(view.date_begin_remind_add)
        }

        view.time_begin_remind_add.setOnClickListener {
            getTime(view.time_begin_remind_add)
        }

        view.save_remind_button.setOnClickListener {
            insertDataToDatabase()
        }

        view.state_image_remind.setImageResource(R.drawable.status_event_green)

        return view
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


    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertDataToDatabase() {
        val title = title_remind_add.text.toString()
        val dateBegin = date_begin_remind_add.text.toString()
        val timeBegin = time_begin_remind_add.text.toString()
        val state = state_remind_add.text.toString()
        val description = description_remind_add.text.toString()
        val location = location_remind_add.text.toString()

        val rid = randomId()


        if (inputCheck(title, dateBegin, timeBegin, state, description)) {

            val event = Event(
                rid, title, dateBegin, "-", timeBegin, "-", state, description, "-",
                "-", "-", "-", "-", "-",
                "-", "-", "-", "-", location, 2
            )

            val eventCalendar = EventCalendar(
                rid,
                3,
                dateBegin.substring(0, 2).toInt(),
                dateBegin.substring(3, 5).toInt() - 1,
                dateBegin.substring(6, 10).toInt(),
                title
            )

            mEventViewModel.addEvent(event)
            mEventCalendar.addEventCalendar(eventCalendar)

            val destinationDateTime = "$dateBegin $timeBegin:00"
            if (dateBegin != "" && timeBegin != "") {
                val dt1 = "${dateBegin} ${timeBegin}:00"
                val rId1 = "1${rid}".toInt()
                setAlarm(rId1, title, state, destinationDateTime, dt1)
            }

            Toast.makeText(requireContext(), "Successfully add!", Toast.LENGTH_SHORT).show()

            val action =
                ReminderNotificationAddDirections.actionReminderNotificationAddToEventNotification()
            findNavController().navigate(action)

        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()


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


    private fun inputCheck(
        title: String,
        dateBegin: String,
        timeBegin: String,
        state: String,
        description: String
    ): Boolean {

        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(dateBegin) || timeBegin.equals("") || state.equals(
            ""
        )
                || description.equals(""))

    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun roundTime(): LocalDateTime {

        var date = LocalDateTime.now(ZoneId.of("Asia/Bangkok"))

        if (date.minute >= 45) {
            return LocalDateTime.now().plusHours(1).withMinute(0)
        } else if (date.minute >= 0 && date.minute < 15) {
            return LocalDateTime.now().withMinute(15)
        } else if (date.minute >= 15 && date.minute < 30) {
            return LocalDateTime.now().withMinute(30)
        } else if (date.minute >= 30 && date.minute < 45) {
            return LocalDateTime.now().withMinute(45)
        } else {
            return date
        }
    }

    private fun setState(tv: TextView, im: ImageView) {

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

    private fun randomId(): Int {
        var randomId =
            "1${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}".toInt()
        val list = arrayListOf<Int>()
        var checked = false

        mEventCalendar.readAllData.observe(viewLifecycleOwner, { event ->
            mCalendar = event

            for (i in 0..mCalendar.size - 1) {

                list.add(mCalendar[i].id)
            }

            while (checked == false) {
                if (randomId in list) {
                    randomId =
                        "1${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}".toInt()
                    println(randomId)
                } else {
                    checked = true
                }
            }
        })
        return randomId
    }


}