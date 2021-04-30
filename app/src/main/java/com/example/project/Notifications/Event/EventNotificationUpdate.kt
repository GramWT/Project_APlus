package com.example.project.Notifications.Event

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.Event
import com.example.project.DataBase.model.EventCalendar
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.EventViewModel
import com.example.project.R
import kotlinx.android.synthetic.main.dialog_select_previous_time.view.*
import kotlinx.android.synthetic.main.fragment_event_notification_add.*
import kotlinx.android.synthetic.main.fragment_event_notification_add.view.*
import kotlinx.android.synthetic.main.fragment_event_notification_update.*
import kotlinx.android.synthetic.main.fragment_event_notification_update.state_image
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.*
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.date_1
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.date_2
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.date_3
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.date_4
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.date_5
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.state_image
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.time_1
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.time_2
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.time_3
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.time_4
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.time_5
import kotlinx.android.synthetic.main.fragment_event_notification_view.view.*
import kotlinx.android.synthetic.main.fragment_subjects_manage_update.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class EventNotificationUpdate : Fragment() {

    private val args by navArgs<EventNotificationUpdateArgs>()
    private lateinit var mEventViewModel: EventViewModel
    private lateinit var mEventCalendarViewModel: EventCalendarViewModel
    private lateinit var mAlarmService: AlarmService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_event_notification_update, container, false)

        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        mEventCalendarViewModel = ViewModelProvider(this).get(EventCalendarViewModel::class.java)
        mAlarmService = AlarmService(requireContext())

        val currentItem = args.event

        setNotification(view)


        view.title_event_update.setText(args.event.title)
        view.date_begin_event_update.setText(args.event.date_begin)
        view.date_end_event_update.setText(args.event.date_end)
        view.time_begin_event_update.setText(args.event.time_begin)
        view.time_end_event_update.setText(args.event.time_end)
        view.location_event_update.setText(args.event.location)

        view.date_1.text = args.event.date_notification_1
        view.date_2.text = args.event.date_notification_2
        view.date_3.text = args.event.date_notification_3
        view.date_4.text = args.event.date_notification_4
        view.date_5.text = args.event.date_notification_5
        view.time_1.text = args.event.time_notification_1
        view.time_2.text = args.event.time_notification_2
        view.time_3.text = args.event.time_notification_3
        view.time_4.text = args.event.time_notification_4
        view.time_5.text = args.event.time_notification_5

        view.description_event_update.setText(args.event.description)
        view.state_event_update.setText(args.event.state)
        setState(args.event.state,view.state_image)

        setShowNotification(view)

        view.add_button.setOnClickListener {

            dialogPreviousTime(view)

        }

        view.cancel_time_1.setOnClickListener {
            sortNotification1(view)
            checkNotification()
        }

        view.cancel_time_2.setOnClickListener {
            sortNotification2(view)
            checkNotification()
        }

        view.cancel_time_3.setOnClickListener {
            sortNotification3(view)
            checkNotification()
        }

        view.cancel_time_4.setOnClickListener {
            sortNotification4(view)
            checkNotification()
        }

        view.cancel_time_5.setOnClickListener {
            sortNotification5(view)
            checkNotification()
        }



        view.cancel_button_event_update.setOnClickListener {
            val action = EventNotificationUpdateDirections.actionEventNotificationUpdateToEventNotificationView(currentItem)
            findNavController().navigate(action)
        }

        view.save_button_event_update.setOnClickListener {
            updateItem()
            val event = Event(args.event.id,title_event_update.text.toString(),
                    date_begin_event_update.text.toString(),
                    date_end_event_update.text.toString(),
                    time_begin_event_update.text.toString(),
                    time_end_event_update.text.toString(),
                    state_event_update.text.toString(),
                    description_event_update.text.toString(),
                    date_1.text.toString(),
                    date_2.text.toString(),
                    date_3.text.toString(),
                    date_4.text.toString(),
                    date_5.text.toString(),
                    time_1.text.toString(),
                    time_2.text.toString(),
                    time_3.text.toString(),
                    time_4.text.toString(),
                    time_5.text.toString(),
                    location_event_update.text.toString())


            val action = EventNotificationUpdateDirections.actionEventNotificationUpdateToEventNotificationView(event)
            Toast.makeText(requireContext(),"Update Successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(action)

        }

        view.date_begin_event_update.setOnClickListener {
            getDate(view.date_begin_event_update)
        }

        view.date_end_event_update.setOnClickListener {
            getDate(view.date_end_event_update)
        }

        view.time_begin_event_update.setOnClickListener {
            getTime(view.time_begin_event_update)
        }

        view.time_end_event_update.setOnClickListener {
            getTime(view.time_end_event_update)
        }

        view.state_event_update.setOnClickListener {
            setStateView(view.state_event_update,state_image)
        }


        return view
    }

    private fun checkNotification() {
        if (notification_5.visibility == View.GONE || notification_4.visibility == View.GONE || notification_3.visibility == View.GONE
                || notification_2.visibility == View.GONE || notification_1.visibility == View.GONE){
            add_notification.visibility = View.VISIBLE
        }
    }

    private fun sortNotification5(view: View){
        if (view.notification_5.visibility == View.VISIBLE){

            view.notification_5.visibility = View.GONE
            view.date_5.text = ""
            view.time_5.text = ""
        }
    }

    private fun sortNotification4(view: View){
        if (view.notification_5.visibility == View.VISIBLE){

            view.notification_4.visibility = View.VISIBLE
            view.notification_5.visibility = View.GONE

            view.date_4.text = view.date_5.text
            view.time_4.text = view.time_5.text
            view.date_5.text = ""
            view.time_5.text = ""
        }
        else if (view.notification_5.visibility == View.GONE){

            view.notification_4.visibility = View.GONE
            view.date_4.text = ""
            view.time_4.text = ""
        }
    }

    private fun sortNotification3(view: View){
        if (view.notification_5.visibility == View.VISIBLE &&
                view.notification_4.visibility == View.VISIBLE){

            view.notification_3.visibility = View.VISIBLE
            view.notification_4.visibility = View.VISIBLE
            view.notification_5.visibility = View.GONE

            view.date_3.text = view.date_4.text
            view.time_3.text = view.time_4.text
            view.date_4.text = view.date_5.text
            view.time_4.text = view.time_5.text
            view.date_5.text = ""
            view.time_5.text = ""
        }
        else if (view.notification_5.visibility == View.GONE &&
                view.notification_4.visibility == View.VISIBLE){

            view.notification_3.visibility = View.VISIBLE
            view.notification_4.visibility = View.GONE

            view.date_3.text = view.date_4.text
            view.time_3.text = view.time_4.text
            view.date_4.text = ""
            view.time_4.text = ""
        }
        else if (view.notification_5.visibility == View.GONE &&
                view.notification_4.visibility == View.GONE){
            view.notification_3.visibility = View.GONE
            view.date_3.text = ""
            view.time_3.text = ""
        }
    }

    private fun sortNotification2(view: View){
        if (view.notification_5.visibility == View.VISIBLE &&
                view.notification_4.visibility == View.VISIBLE &&
                view.notification_3.visibility == View.VISIBLE){

            view.notification_2.visibility = View.VISIBLE
            view.notification_3.visibility = View.VISIBLE
            view.notification_4.visibility = View.VISIBLE
            view.notification_5.visibility = View.GONE

            view.date_2.text = view.date_3.text
            view.time_2.text = view.time_3.text
            view.date_3.text = view.date_4.text
            view.time_3.text = view.time_4.text
            view.date_4.text = view.date_5.text
            view.time_4.text = view.time_5.text
            view.date_5.text = ""
            view.time_5.text = ""
        }
        else if (view.notification_5.visibility == View.GONE &&
                view.notification_4.visibility == View.VISIBLE &&
                view.notification_3.visibility == View.VISIBLE){

            view.notification_2.visibility = View.VISIBLE
            view.notification_3.visibility = View.VISIBLE
            view.notification_4.visibility = View.GONE

            view.date_2.text = view.date_3.text
            view.time_2.text = view.time_3.text
            view.date_3.text = view.date_4.text
            view.time_3.text = view.time_4.text
            view.date_4.text = ""
            view.time_4.text = ""
        }
        else if (view.notification_5.visibility == View.GONE &&
                view.notification_4.visibility == View.GONE &&
                view.notification_3.visibility == View.VISIBLE){

            view.notification_2.visibility = View.VISIBLE
            view.notification_3.visibility = View.GONE

            view.date_2.text = view.date_3.text
            view.time_2.text = view.time_3.text
            view.date_3.text = ""
            view.time_3.text = ""
        }
        else if (view.notification_5.visibility == View.GONE &&
                view.notification_4.visibility == View.GONE &&
                view.notification_3.visibility == View.GONE){

            view.notification_2.visibility = View.GONE
            view.date_2.text = ""
            view.time_2.text = ""
        }
    }

    private fun sortNotification1(view: View){
        if (view.notification_5.visibility == View.VISIBLE &&
                view.notification_4.visibility == View.VISIBLE &&
                view.notification_3.visibility == View.VISIBLE &&
                view.notification_2.visibility == View.VISIBLE){

            view.notification_1.visibility = View.VISIBLE
            view.notification_2.visibility = View.VISIBLE
            view.notification_3.visibility = View.VISIBLE
            view.notification_4.visibility = View.VISIBLE
            view.notification_5.visibility = View.GONE

            view.date_1.text = view.date_2.text
            view.time_1.text = view.time_2.text
            view.date_2.text = view.date_3.text
            view.time_2.text = view.time_3.text
            view.date_3.text = view.date_4.text
            view.time_3.text = view.time_4.text
            view.date_4.text = view.date_5.text
            view.time_4.text = view.time_5.text
            view.date_5.text = ""
            view.time_5.text = ""
        }
        else if (view.notification_5.visibility == View.GONE &&
                view.notification_4.visibility == View.VISIBLE &&
                view.notification_3.visibility == View.VISIBLE &&
                view.notification_2.visibility == View.VISIBLE){

            view.notification_1.visibility = View.VISIBLE
            view.notification_2.visibility = View.VISIBLE
            view.notification_3.visibility = View.VISIBLE
            view.notification_4.visibility = View.GONE

            view.date_1.text = view.date_2.text
            view.time_1.text = view.time_2.text
            view.date_2.text = view.date_3.text
            view.time_2.text = view.time_3.text
            view.date_3.text = view.date_4.text
            view.time_3.text = view.time_4.text
            view.date_4.text = ""
            view.time_4.text = ""
        }
        else if (view.notification_5.visibility == View.GONE &&
                view.notification_4.visibility == View.GONE &&
                view.notification_3.visibility == View.VISIBLE &&
                view.notification_2.visibility == View.VISIBLE){

            view.notification_1.visibility = View.VISIBLE
            view.notification_2.visibility = View.VISIBLE
            view.notification_3.visibility = View.GONE

            view.date_1.text = view.date_2.text
            view.time_1.text = view.time_2.text
            view.date_2.text = view.date_3.text
            view.time_2.text = view.time_3.text
            view.date_3.text = ""
            view.time_3.text = ""
        }
        else if (view.notification_5.visibility == View.GONE &&
                view.notification_4.visibility == View.GONE &&
                view.notification_3.visibility == View.GONE &&
                view.notification_2.visibility == View.VISIBLE){

            view.notification_1.visibility = View.VISIBLE
            view.notification_2.visibility = View.GONE

            view.date_1.text = view.date_2.text
            view.time_1.text = view.time_2.text
            view.date_2.text = ""
            view.time_2.text = ""
        }
        else if (view.notification_5.visibility == View.GONE &&
                view.notification_4.visibility == View.GONE &&
                view.notification_3.visibility == View.GONE &&
                view.notification_2.visibility == View.GONE){

            view.notification_1.visibility = View.GONE

            view.date_1.text = ""
            view.time_1.text = ""
        }
    }

    private fun updateItem(){
        val event = Event(args.event.id,title_event_update.text.toString(),
                date_begin_event_update.text.toString(),
                date_end_event_update.text.toString(),
                time_begin_event_update.text.toString(),
                time_end_event_update.text.toString(),
                state_event_update.text.toString(),
                description_event_update.text.toString(),
                date_1.text.toString(),
                date_2.text.toString(),
                date_3.text.toString(),
                date_4.text.toString(),
                date_5.text.toString(),
                time_1.text.toString(),
                time_2.text.toString(),
                time_3.text.toString(),
                time_4.text.toString(),
                time_5.text.toString(),
                location_event_update.text.toString())

        val eventMid = EventCalendar(args.event.id,2,date_begin_event_update.text.substring(0,2).toInt(),date_begin_event_update.text.substring(3,5).toInt() - 1,
                date_begin_event_update.text.substring(6,10).toInt(),title_event_update.text.toString())

        mEventViewModel.updateEvent(event)
        mEventCalendarViewModel.updateEventCalendar(eventMid)

        if (date_1.text.toString() != "" && time_1.text.toString() != ""){
            val dt1 = "${date_1.text} ${time_1.text}:00"
            val rId1 = "1${args.event.id}".toInt()
            println(rId1)
            setAlarm(dt1 ,rId1,args.event.id.toString())
        }
        if (date_2.text.toString() != "" && time_2.text.toString() != ""){
            val dt2 = "${date_2.text} ${time_2.text}:00"
            val rId2 = "2${args.event.id}".toInt()
            println(rId2)
            setAlarm(dt2 ,rId2,args.event.id.toString())
        }
        if (date_3.text.toString() != "" && time_3.text.toString() != ""){
            val dt3 = "${date_3.text} ${time_3.text}:00"
            val rId3 = "3${args.event.id}".toInt()
            println(rId3)
            setAlarm(dt3 ,rId3,args.event.id.toString())
        }
        if (date_4.text.toString() != "" && time_4.text.toString() != ""){
            val dt4 = "${date_4.text} ${time_4.text}:00"
            val rId4 = "4${args.event.id}".toInt()
            println(rId4)
            setAlarm(dt4 ,rId4,args.event.id.toString())
        }
        if (date_5.text.toString() != "" && time_5.text.toString() != ""){
            val dt5 = "${date_5.text} ${time_5.text}:00"
            val rId5 = "5${args.event.id}".toInt()
            println(rId5)
            setAlarm(dt5 ,rId5,args.event.id.toString())
        }

    }

    private fun setAlarm(date:String,rq:Int,SID:String){
        mAlarmService.setOnceAlarm(convertMillis(date),rq,SID)
    }

    private fun setState(state: String, im: ImageView){
        when(state){
            "High" -> im.setImageResource(R.drawable.status_event_red)
            "Normal" -> im.setImageResource(R.drawable.status_event_green)
            "Low" -> im.setImageResource(R.drawable.status_event_blue)
        }
    }

    private fun setStateView(tv: TextView, im: ImageView){

        val ListState = arrayOf("High","Normal","Low","Default")

        val BBuilder = AlertDialog.Builder(requireContext())

        BBuilder.setTitle("Choose State")

        BBuilder.setSingleChoiceItems(ListState,-1){dialog,i->

            tv.setText(ListState[i])
            dialog.dismiss()
            when(ListState[i]){
                "High" -> im.setImageResource(R.drawable.status_event_red)
                "Normal" -> im.setImageResource(R.drawable.status_event_green)
                "Low" -> im.setImageResource(R.drawable.status_event_blue)
            }
        }

        BBuilder.show()
    }

    private fun getTime(tv: TextView){
        val cal = Calendar.getInstance()
        val timeSet = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY,hour)
            cal.set(Calendar.MINUTE,minute)

            tv.text = SimpleDateFormat("HH:mm").format(cal.time).toString()
        }
        TimePickerDialog(requireContext(), AlertDialog.THEME_HOLO_DARK,timeSet,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
    }

    private fun getDate(tv: TextView){
        val cal = Calendar.getInstance()
        val dpd = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.YEAR,year)



            tv.text = SimpleDateFormat("dd/MM/yyyy").format(cal.time).toString()


        }

        DatePickerDialog(requireContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()

    }

    private fun setNotification(view: View){
        view.notification_1.visibility = View.GONE
        view.notification_2.visibility = View.GONE
        view.notification_3.visibility = View.GONE
        view.notification_4.visibility = View.GONE
        view.notification_5.visibility = View.GONE
    }

    private fun setShowNotification(view: View){
        if (view.time_1.text != ""){
            view.notification_1.visibility = View.VISIBLE
        }
        if (view.time_2.text != ""){
            view.notification_2.visibility = View.VISIBLE
        }
        if (view.time_3.text != ""){
            view.notification_3.visibility = View.VISIBLE
        }
        if (view.time_4.text != ""){
            view.notification_4.visibility = View.VISIBLE
        }
        if (view.time_5.text != ""){
            view.notification_5.visibility = View.VISIBLE
        }
        if (view.notification_1.visibility == View.VISIBLE && view.notification_2.visibility == View.VISIBLE
                && view.notification_3.visibility == View.VISIBLE && view.notification_4.visibility == View.VISIBLE
                && view.notification_5.visibility == View.VISIBLE) {
            view.add_notification.visibility = View.GONE
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun dialogPreviousTime(view:View){
        val selectPreviousTime = LayoutInflater.from(context).inflate(R.layout.dialog_select_previous_time, null)
        val mBuilder = AlertDialog.Builder(context)
                .setView(selectPreviousTime)

        var timeValue: String = ""
        var TS:String = ""

        selectPreviousTime.radio_group_previous_time.check(R.id.radio_minutes)
        selectPreviousTime.radio_minutes.text = "Minutes before"
        TS = "M"

        selectPreviousTime.previous_time.filters = arrayOf<InputFilter>(MinMaxFilter("1","600"))

        val mAlert = mBuilder.show()





        selectPreviousTime.radio_group_previous_time.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radio_minutes) {
                if (selectPreviousTime.previous_time.text.toString() == ""){
                    selectPreviousTime.previous_time.setText("10")
                }
                selectPreviousTime.previous_time.filters = arrayOf<InputFilter>(MinMaxFilter("1","600"))
                selectPreviousTime.radio_minutes.text = "Minutes before"
                selectPreviousTime.radio_hours.text = "Hours"
                selectPreviousTime.radio_days.text = "Days"
                selectPreviousTime.radio_weeks.text = "Weeks"
                TS = "M"}
            if (checkedId == R.id.radio_hours) {
                if (selectPreviousTime.previous_time.text.toString() == ""){
                    selectPreviousTime.previous_time.setText("1")
                }
                if (Integer.parseInt(selectPreviousTime.previous_time.text.toString()) > 120){
                    selectPreviousTime.previous_time.setText("120")
                }
                selectPreviousTime.previous_time.filters = arrayOf<InputFilter>(MinMaxFilter("1","120"))
                selectPreviousTime.radio_minutes.text = "Minutes"
                selectPreviousTime.radio_hours.text = "Hours before"
                selectPreviousTime.radio_days.text = "Days"
                selectPreviousTime.radio_weeks.text = "Weeks"
                TS = "H"}
            if (checkedId == R.id.radio_days) {
                if (selectPreviousTime.previous_time.text.toString() == ""){
                    selectPreviousTime.previous_time.setText("1")
                }
                if (Integer.parseInt(selectPreviousTime.previous_time.text.toString()) > 28){
                    selectPreviousTime.previous_time.setText("28")
                }
                selectPreviousTime.previous_time.filters = arrayOf<InputFilter>(MinMaxFilter("1","28"))
                selectPreviousTime.radio_minutes.text = "Minutes"
                selectPreviousTime.radio_hours.text = "Hours"
                selectPreviousTime.radio_days.text = "Days before"
                selectPreviousTime.radio_weeks.text = "Weeks"
                TS = "D"}
            if (checkedId == R.id.radio_weeks) {
                if (selectPreviousTime.previous_time.text.toString() == ""){
                    selectPreviousTime.previous_time.setText("1")
                }
                if (Integer.parseInt(selectPreviousTime.previous_time.text.toString()) > 4){
                    selectPreviousTime.previous_time.setText("4")
                }
                selectPreviousTime.previous_time.filters = arrayOf<InputFilter>(MinMaxFilter("1","4"))
                selectPreviousTime.radio_minutes.text = "Minutes"
                selectPreviousTime.radio_hours.text = "Hours"
                selectPreviousTime.radio_days.text = "Days"
                selectPreviousTime.radio_weeks.text = "Weeks before"
                TS = "W"}
        }

        selectPreviousTime.done_button.setOnClickListener {
            mAlert.dismiss()
            timeValue = selectPreviousTime.previous_time.text.toString()

            addNotifications(view,timeValue,TS)
        }
    }

    private fun convertDateTime(timeInMillis: Long): String =
            DateFormat.format("dd/MM/yyyy HH:mm",timeInMillis).toString()

    private fun convertDate(timeInMillis: Long): String =
            DateFormat.format("dd/MM/yyyy",timeInMillis).toString()

    private fun convertTime(timeInMillis: Long): String =
            DateFormat.format("HH:mm",timeInMillis).toString()

    private fun convertMillis(data: String):Long{
        var sp = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        var date:Date = sp.parse(data)
        var millis:Long = date.time

        return millis
    }

    inner class MinMaxFilter(): InputFilter {
        private var intMin: Int = 0
        private var intMax: Int = 100
        constructor(minValue: String, maxValue: String) : this() {
            this.intMin = Integer.parseInt(minValue)
            this.intMax = Integer.parseInt(maxValue)
        }

        override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
            try {

                val input = Integer.parseInt(dest.toString() + source.toString())
                if (isInRange(intMin, intMax, input)) {
                    return null
                }
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
            return ""
        }
        private fun isInRange(a: Int, b: Int, c: Int): Boolean {
            return if (b > a) c in a..b else c in b..a
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addNotifications(view: View, s: String, ts:String) {
        val date = view.date_begin_event_update.text.toString()
        val time = view.time_begin_event_update.text.toString()

        val datetime = "$date $time:00"
        var Date = convertMillis(datetime)


        if (ts == "M") {
            Date = convertMillis(datetime) - TimeUnit.MINUTES.toMillis(s.toLong())

        } else if (ts == "H") {
            Date = convertMillis(datetime) - TimeUnit.HOURS.toMillis(s.toLong())

        } else if (ts == "D") {
            Date = convertMillis(datetime) - TimeUnit.DAYS.toMillis(s.toLong())
        } else if (ts == "W") {

            val rs = s.toInt() * 7

            Date = convertMillis(datetime) - TimeUnit.DAYS.toMillis(rs.toLong())
        }

        val checkDatetime = convertDateTime(Date)
        val notificationDate = convertDate(Date)
        val notificationTime = convertTime(Date)


        if (checkDatetime != "${view.date_1.text} ${view.time_1.text}" &&
                checkDatetime != "${view.date_2.text} ${view.time_2.text}" &&
                checkDatetime != "${view.date_3.text} ${view.time_3.text}" &&
                checkDatetime != "${view.date_4.text} ${view.time_4}" &&
                checkDatetime != "${view.date_5.text} ${view.time_5.text}") {

            if (view.notification_1.visibility == View.GONE) {
                view.notification_1.visibility = View.VISIBLE
                view.date_1.text = notificationDate
                view.time_1.text = notificationTime
            } else if (view.notification_2.visibility == View.GONE) {
                view.notification_2.visibility = View.VISIBLE
                view.date_2.text = notificationDate
                view.time_2.text = notificationTime
            } else if (view.notification_3.visibility == View.GONE) {
                view.notification_3.visibility = View.VISIBLE
                view.date_3.text = notificationDate
                view.time_3.text = notificationTime
            } else if (view.notification_4.visibility == View.GONE) {
                view.notification_4.visibility = View.VISIBLE
                view.date_4.text = notificationDate
                view.time_4.text = notificationTime
            } else if (view.notification_5.visibility == View.GONE) {
                view.notification_5.visibility = View.VISIBLE
                view.date_5.text = notificationDate
                view.time_5.text = notificationTime
            }
            if (view.notification_5.visibility == View.VISIBLE && view.notification_4.visibility == View.VISIBLE
                    && view.notification_3.visibility == View.VISIBLE && view.notification_2.visibility == View.VISIBLE
                    && view.notification_1.visibility == View.VISIBLE) {
                view.add_notification.visibility = View.GONE
            }

            sortTime(view)


        }

    }

    private fun sortTime(view: View){

        var d1 = ""
        var d2 = ""
        var t1 = ""
        var t2 = ""

        if (view.notification_5.visibility == View.VISIBLE){
            var datetime1 = "${view.date_1.text} ${view.time_1.text}:00"
            var datetime2 = "${view.date_2.text} ${view.time_2.text}:00"
            var datetime3 = "${view.date_3.text} ${view.time_3.text}:00"
            var datetime4 = "${view.date_4.text} ${view.time_4.text}:00"
            var datetime5 = "${view.date_5.text} ${view.time_5.text}:00"

            if (convertMillis(datetime5) < convertMillis(datetime4)){
                d1 = view.date_5.text.toString()
                t1 = view.time_5.text.toString()
                d2 = view.date_4.text.toString()
                t2 = view.time_4.text.toString()

                view.date_5.text = d2
                view.time_5.text = t2
                view.date_4.text = d1
                view.time_4.text = t1

            }


            datetime3 = "${view.date_3.text} ${view.time_3.text}:00"
            datetime4 = "${view.date_4.text} ${view.time_4.text}:00"


            if (convertMillis(datetime4) < convertMillis(datetime3)){
                d1 = view.date_4.text.toString()
                t1 = view.time_4.text.toString()
                d2 = view.date_3.text.toString()
                t2 = view.time_3.text.toString()

                view.date_4.text = d2
                view.time_4.text = t2
                view.date_3.text = d1
                view.time_3.text = t1

            }

            datetime2 = "${view.date_2.text} ${view.time_2.text}:00"
            datetime3 = "${view.date_3.text} ${view.time_3.text}:00"

            if (convertMillis(datetime3) < convertMillis(datetime2)){
                d1 = view.date_3.text.toString()
                t1 = view.time_3.text.toString()
                d2 = view.date_2.text.toString()
                t2 = view.time_2.text.toString()

                view.date_3.text = d2
                view.time_3.text = t2
                view.date_2.text = d1
                view.time_2.text = t1

            }

            datetime1 = "${view.date_1.text} ${view.time_1.text}:00"
            datetime2 = "${view.date_2.text} ${view.time_2.text}:00"

            if (convertMillis(datetime2) < convertMillis(datetime1)){
                d1 = view.date_2.text.toString()
                t1 = view.time_2.text.toString()
                d2 = view.date_1.text.toString()
                t2 = view.time_1.text.toString()

                view.date_2.text = d2
                view.time_2.text = t2
                view.date_1.text = d1
                view.time_1.text = t1

            }
        }

        else if (view.notification_4.visibility == View.VISIBLE) {
            var datetime1 = "${view.date_1.text} ${view.time_1.text}:00"
            var datetime2 = "${view.date_2.text} ${view.time_2.text}:00"
            var datetime3 = "${view.date_3.text} ${view.time_3.text}:00"
            var datetime4 = "${view.date_4.text} ${view.time_4.text}:00"
            var datetime5 = "${view.date_5.text} ${view.time_5.text}:00"

            if (convertMillis(datetime4) < convertMillis(datetime3)) {
                d1 = view.date_4.text.toString()
                t1 = view.time_4.text.toString()
                d2 = view.date_3.text.toString()
                t2 = view.time_3.text.toString()

                view.date_4.text = d2
                view.time_4.text = t2
                view.date_3.text = d1
                view.time_3.text = t1

            }

            datetime2 = "${view.date_2.text} ${view.time_2.text}:00"
            datetime3 = "${view.date_3.text} ${view.time_3.text}:00"

            if (convertMillis(datetime3) < convertMillis(datetime2)){
                d1 = view.date_3.text.toString()
                t1 = view.time_3.text.toString()
                d2 = view.date_2.text.toString()
                t2 = view.time_2.text.toString()

                view.date_3.text = d2
                view.time_3.text = t2
                view.date_2.text = d1
                view.time_2.text = t1

            }

            datetime1 = "${view.date_1.text} ${view.time_1.text}:00"
            datetime2 = "${view.date_2.text} ${view.time_2.text}:00"

            if (convertMillis(datetime2) < convertMillis(datetime1)){
                d1 = view.date_2.text.toString()
                t1 = view.time_2.text.toString()
                d2 = view.date_1.text.toString()
                t2 = view.time_1.text.toString()

                view.date_2.text = d2
                view.time_2.text = t2
                view.date_1.text = d1
                view.time_1.text = t1

            }

        }

        else if (view.notification_3.visibility == View.VISIBLE) {
            var datetime1 = "${view.date_1.text} ${view.time_1.text}:00"
            var datetime2 = "${view.date_2.text} ${view.time_2.text}:00"
            var datetime3 = "${view.date_3.text} ${view.time_3.text}:00"

            if (convertMillis(datetime3) < convertMillis(datetime2)){
                d1 = view.date_3.text.toString()
                t1 = view.time_3.text.toString()
                d2 = view.date_2.text.toString()
                t2 = view.time_2.text.toString()

                view.date_3.text = d2
                view.time_3.text = t2
                view.date_2.text = d1
                view.time_2.text = t1

            }

            datetime1 = "${view.date_1.text} ${view.time_1.text}:00"
            datetime2 = "${view.date_2.text} ${view.time_2.text}:00"


            if (convertMillis(datetime2) < convertMillis(datetime1)){
                d1 = view.date_2.text.toString()
                t1 = view.time_2.text.toString()
                d2 = view.date_1.text.toString()
                t2 = view.time_1.text.toString()

                view.date_2.text = d2
                view.time_2.text = t2
                view.date_1.text = d1
                view.time_1.text = t1

            }
        }

        else if (view.notification_2.visibility == View.VISIBLE) {
            val datetime1 = "${view.date_1.text} ${view.time_1.text}:00"
            val datetime2 = "${view.date_2.text} ${view.time_2.text}:00"

            if (convertMillis(datetime2) < convertMillis(datetime1)){
                d1 = view.date_2.text.toString()
                t1 = view.time_2.text.toString()
                d2 = view.date_1.text.toString()
                t2 = view.time_1.text.toString()

                view.date_2.text = d2
                view.time_2.text = t2
                view.date_1.text = d1
                view.time_1.text = t1

            }
        }
    }


}