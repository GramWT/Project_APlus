package com.example.project.Notifications.Event

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.text.TextUtils
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.Event
import com.example.project.DataBase.model.EventCalendar
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.EventViewModel
import com.example.project.MainActivity
import com.example.project.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_select_previous_time.view.*
import kotlinx.android.synthetic.main.fragment_event_notification_add.*
import kotlinx.android.synthetic.main.fragment_event_notification_add.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit


class EventNotificationAdd : Fragment() {

    private lateinit var mEventViewModel:EventViewModel
    private lateinit var mEventCalendar: EventCalendarViewModel
    private lateinit var mAlarmService: AlarmService
    private lateinit var mCalendar:List<EventCalendar>


    override fun onResume() {

        super.onResume()
        val a = activity as MainActivity
        a.bottom_navigation.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        val a = activity as MainActivity
        a.bottom_navigation.visibility = View.VISIBLE
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_event_notification_add, container, false)


        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        mEventCalendar = ViewModelProvider(this).get(EventCalendarViewModel::class.java)

        mAlarmService = AlarmService(requireContext())


        view.date_begin_event_add.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(roundTime()))

        view.date_end_event_add.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(roundTime().plusHours(1)))

        view.time_begin_event_add.setText(DateTimeFormatter.ofPattern("HH:mm").format(roundTime()))

        view.time_end_event_add.setText(DateTimeFormatter.ofPattern("HH:mm").format(roundTime().plusHours(1)))

        view.state_image.setImageResource(R.drawable.status_event_green)

        setVisibility(view)



        view.cancel_notification_device_5.setOnClickListener {
            sortNotification5(view)
            checkNotification()
        }

        view.cancel_notification_device_4.setOnClickListener {
            sortNotification4(view)
            checkNotification()
        }

        view.cancel_notification_device_3.setOnClickListener {
            sortNotification3(view)
            checkNotification()
        }

        view.cancel_notification_device_2.setOnClickListener {
            sortNotification2(view)
            checkNotification()
        }
        view.cancel_notification_device_1.setOnClickListener {
            sortNotification1(view)
            checkNotification()
        }

        view.add_notification.setOnClickListener {
            dialogPreviousTime(view)
        }


        view.save_button.setOnClickListener {
            insertDataToDatabase()
        }

        view.cancel_button.setOnClickListener {
            val action = EventNotificationAddDirections.actionEventNotificationAddToEventNotification()
            findNavController().navigate(action)
        }

        view.date_begin_event_add.setOnClickListener {
            getDate(view.date_begin_event_add)
        }

        view.date_end_event_add.setOnClickListener {
            getDate(view.date_end_event_add)
        }

        view.time_begin_event_add.setOnClickListener {
            getTime(view.time_begin_event_add)
        }

        view.time_end_event_add.setOnClickListener {
            getTime(view.time_end_event_add)
        }

        view.state_event_add.setOnClickListener {
             setState(view.state_event_add,view.state_image)
        }

        return view
    }


    private fun checkNotification() {
        if (notification_device_5.visibility == View.GONE || notification_device_4.visibility == View.GONE || notification_device_3.visibility == View.GONE
                || notification_device_2.visibility == View.GONE || notification_device_1.visibility == View.GONE){
            add_notification_device.visibility = View.VISIBLE
        }
    }

    private fun insertDataToDatabase(){
        val title = title_event_add.text.toString()
        val dateBegin = date_begin_event_add.text.toString()
        val dateEnd = date_end_event_add.text.toString()
        val timeBegin = time_begin_event_add.text.toString()
        val timeEnd = time_end_event_add.text.toString()
        val state = state_event_add.text.toString()
        val description = description_event_add.text.toString()
        val location = location_event_add.text.toString()
        val t1 = notification_date_1
        val t2 = notification_date_2
        val t3 = notification_date_3
        val t4 = notification_date_4
        val t5 = notification_date_5


        val timeNotification = organizeListTime(t1,t2,t3,t4,t5)

        println(timeNotification)

        val rid = randomId()


        if (inputCheck(title,dateBegin,dateEnd,timeBegin,timeEnd,state,description)){
            val event = Event(0 ,title,dateBegin,dateEnd,timeBegin,timeEnd,state,description, timeNotification[0].toString(),timeNotification[1].toString(),
                    timeNotification[2].toString(),timeNotification[3].toString(),timeNotification[4].toString(),location)

            val eventCalendar = EventCalendar(rid,2,dateBegin.substring(0,2).toInt(),dateBegin.substring(3,5).toInt() - 1,dateBegin.substring(6,10).toInt(),title)

            println(dateBegin.substring(0,2).toInt())
            println(dateBegin.substring(3,5).toInt())
            println(dateBegin.substring(6,10).toInt())
            println(timeNotification[0].toString())
            println(timeNotification[1].toString())
            println(timeNotification[2].toString())
            println(timeNotification[3].toString())
            println(timeNotification[4].toString())

            val timeData = "$dateBegin $timeBegin:00"

            println(timeData)

            mEventViewModel.addEvent(event)
            mEventCalendar.addEventCalendar(eventCalendar)

            setAlarm(timeData ,rid,rid.toString())
            println("RID ${rid}")



            Toast.makeText(requireContext(),"Successfully add!", Toast.LENGTH_SHORT).show()

            val action = EventNotificationAddDirections.actionEventNotificationAddToEventNotification()
            findNavController().navigate(action)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields.", Toast.LENGTH_SHORT).show()


        }
    }

    private fun randomId():Int{
        var randomId = "1${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}".toInt()
        val list = arrayListOf<Int>()
        var checked = false

        mEventCalendar.readAllData.observe(viewLifecycleOwner,{ event ->
            mCalendar = event

            for (i in 0 .. mCalendar.size -1){

                list.add(mCalendar[i].id)
            }

            while (checked == false){
                if (randomId in list){
                    randomId = "1${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}".toInt()
                    println("Check is ${checked}")
                    println(randomId)
                }
                else{
                    checked = true
                    println("Check is ${checked}")
                }
            }
        })
        return randomId
    }

    private fun setAlarm(date:String,rq:Int,SID:String){
        mAlarmService.setOnceAlarm(convertMillis(date),rq,SID)
    }

    private fun convertMillis(data: String):Long{
        var sp = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        var date:Date = sp.parse(data)
        var millis:Long = date.time

        return millis
    }

    private fun inputCheck(title:String, dateBegin:String, dateEnd:String, timeBegin:String, timeEnd:String, state:String, description:String):Boolean{

        return  !(TextUtils.isEmpty(title)|| TextUtils.isEmpty(dateBegin)||dateEnd.equals("")||timeBegin.equals("")||timeEnd.equals("")||state.equals("")
                ||description.equals(""))

    }

    private fun setState(tv: TextView,im: ImageView){

        val ListState = arrayOf("High","Normal","Low")

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

    private fun sortNotification5(view: View){
        if (view.notification_device_5.visibility == View.VISIBLE){

            view.notification_device_5.visibility = View.GONE
            view.notification_date_5.text = ""
        }
    }

    private fun sortNotification4(view: View){
        if (view.notification_device_5.visibility == View.VISIBLE){

            view.notification_device_4.visibility = View.VISIBLE
            view.notification_device_5.visibility = View.GONE

            view.notification_date_4.text = view.notification_date_5.text
            view.notification_date_5.text = ""
        }
        else if (view.notification_device_5.visibility == View.GONE){

            view.notification_device_4.visibility = View.GONE
            view.notification_date_4.text = ""
        }
    }

    private fun sortNotification3(view: View){
        if (view.notification_device_5.visibility == View.VISIBLE &&
                view.notification_device_4.visibility == View.VISIBLE){

            view.notification_device_3.visibility = View.VISIBLE
            view.notification_device_4.visibility = View.VISIBLE
            view.notification_device_5.visibility = View.GONE

            view.notification_date_3.text = view.notification_date_4.text
            view.notification_date_4.text = view.notification_date_5.text
            view.notification_date_5.text = ""
        }
        else if (view.notification_device_5.visibility == View.GONE &&
                view.notification_device_4.visibility == View.VISIBLE){

            view.notification_device_3.visibility = View.VISIBLE
            view.notification_device_4.visibility = View.GONE

            view.notification_date_3.text = view.notification_date_4.text
            view.notification_date_4.text = ""
        }
        else if (view.notification_device_5.visibility == View.GONE &&
                view.notification_device_4.visibility == View.GONE){
            view.notification_device_3.visibility = View.GONE
            view.notification_date_3.text = ""
        }
    }

    private fun sortNotification2(view: View){
        if (view.notification_device_5.visibility == View.VISIBLE &&
                view.notification_device_4.visibility == View.VISIBLE &&
                view.notification_device_3.visibility == View.VISIBLE){

            view.notification_device_2.visibility = View.VISIBLE
            view.notification_device_3.visibility = View.VISIBLE
            view.notification_device_4.visibility = View.VISIBLE
            view.notification_device_5.visibility = View.GONE

            view.notification_date_2.text = view.notification_date_3.text
            view.notification_date_3.text = view.notification_date_4.text
            view.notification_date_4.text = view.notification_date_5.text
            view.notification_date_5.text = ""
        }
        else if (view.notification_device_5.visibility == View.GONE &&
                view.notification_device_4.visibility == View.VISIBLE &&
                view.notification_device_3.visibility == View.VISIBLE){

            view.notification_device_2.visibility = View.VISIBLE
            view.notification_device_3.visibility = View.VISIBLE
            view.notification_device_4.visibility = View.GONE

            view.notification_date_2.text = view.notification_date_3.text
            view.notification_date_3.text = view.notification_date_4.text
            view.notification_date_4.text = ""
        }
        else if (view.notification_device_5.visibility == View.GONE &&
                view.notification_device_4.visibility == View.GONE &&
                view.notification_device_3.visibility == View.VISIBLE){

            view.notification_device_2.visibility = View.VISIBLE
            view.notification_device_3.visibility = View.GONE

            view.notification_date_2.text = view.notification_date_3.text
            view.notification_date_3.text = ""
        }
        else if (view.notification_device_5.visibility == View.GONE &&
                view.notification_device_4.visibility == View.GONE &&
                view.notification_device_3.visibility == View.GONE){

            view.notification_device_2.visibility = View.GONE
            view.notification_date_2.text = ""
        }
    }

    private fun sortNotification1(view: View){
        if (view.notification_device_5.visibility == View.VISIBLE &&
                view.notification_device_4.visibility == View.VISIBLE &&
                view.notification_device_3.visibility == View.VISIBLE &&
                view.notification_device_2.visibility == View.VISIBLE){

            view.notification_device_1.visibility = View.VISIBLE
            view.notification_device_2.visibility = View.VISIBLE
            view.notification_device_3.visibility = View.VISIBLE
            view.notification_device_4.visibility = View.VISIBLE
            view.notification_device_5.visibility = View.GONE

            view.notification_date_1.text = view.notification_date_2.text
            view.notification_date_2.text = view.notification_date_3.text
            view.notification_date_3.text = view.notification_date_4.text
            view.notification_date_4.text = view.notification_date_5.text
            view.notification_date_5.text = ""
        }
        else if (view.notification_device_5.visibility == View.GONE &&
                view.notification_device_4.visibility == View.VISIBLE &&
                view.notification_device_3.visibility == View.VISIBLE &&
                view.notification_device_2.visibility == View.VISIBLE){

            view.notification_device_1.visibility = View.VISIBLE
            view.notification_device_2.visibility = View.VISIBLE
            view.notification_device_3.visibility = View.VISIBLE
            view.notification_device_4.visibility = View.GONE

            view.notification_date_1.text = view.notification_date_2.text
            view.notification_date_2.text = view.notification_date_3.text
            view.notification_date_3.text = view.notification_date_4.text
            view.notification_date_4.text = ""
        }
        else if (view.notification_device_5.visibility == View.GONE &&
                view.notification_device_4.visibility == View.GONE &&
                view.notification_device_3.visibility == View.VISIBLE &&
                view.notification_device_2.visibility == View.VISIBLE){

            view.notification_device_1.visibility = View.VISIBLE
            view.notification_device_2.visibility = View.VISIBLE
            view.notification_device_3.visibility = View.GONE

            view.notification_date_1.text = view.notification_date_2.text
            view.notification_date_2.text = view.notification_date_3.text
            view.notification_date_3.text = ""
        }
        else if (view.notification_device_5.visibility == View.GONE &&
                view.notification_device_4.visibility == View.GONE &&
                view.notification_device_3.visibility == View.GONE &&
                view.notification_device_2.visibility == View.VISIBLE){

            view.notification_device_1.visibility = View.VISIBLE
            view.notification_device_2.visibility = View.GONE

            view.notification_date_1.text = view.notification_date_2.text
            view.notification_date_2.text = ""
        }
        else if (view.notification_device_5.visibility == View.GONE &&
                view.notification_device_4.visibility == View.GONE &&
                view.notification_device_3.visibility == View.GONE &&
                view.notification_device_2.visibility == View.GONE){

            view.notification_device_1.visibility = View.GONE

            view.notification_date_1.text = ""
        }
    }

    private fun sortTime(view: View){

        var d1 = ""
        var d2 = ""
        var t1 = ""
        var t2 = ""

        if (view.notification_device_5.visibility == View.VISIBLE){
            var datetime1 = "${view.notification_date_1.text} ${view.notification_time_1.text}:00"
            var datetime2 = "${view.notification_date_2.text} ${view.notification_time_2.text}:00"
            var datetime3 = "${view.notification_date_3.text} ${view.notification_time_3.text}:00"
            var datetime4 = "${view.notification_date_4.text} ${view.notification_time_4.text}:00"
            var datetime5 = "${view.notification_date_5.text} ${view.notification_time_5.text}:00"

            if (convertMillis(datetime5) < convertMillis(datetime4)){
                d1 = view.notification_date_5.text.toString()
                t1 = view.notification_time_5.text.toString()
                d2 = view.notification_date_4.text.toString()
                t2 = view.notification_time_4.text.toString()

                view.notification_date_5.text = d2
                view.notification_time_5.text = t2
                view.notification_date_4.text = d1
                view.notification_time_4.text = t1

            }


            datetime3 = "${view.notification_date_3.text} ${view.notification_time_3.text}:00"
            datetime4 = "${view.notification_date_4.text} ${view.notification_time_4.text}:00"


            if (convertMillis(datetime4) < convertMillis(datetime3)){
                d1 = view.notification_date_4.text.toString()
                t1 = view.notification_time_4.text.toString()
                d2 = view.notification_date_3.text.toString()
                t2 = view.notification_time_3.text.toString()

                view.notification_date_4.text = d2
                view.notification_time_4.text = t2
                view.notification_date_3.text = d1
                view.notification_time_3.text = t1

            }

            datetime2 = "${view.notification_date_2.text} ${view.notification_time_2.text}:00"
            datetime3 = "${view.notification_date_3.text} ${view.notification_time_3.text}:00"

            if (convertMillis(datetime3) < convertMillis(datetime2)){
                d1 = view.notification_date_3.text.toString()
                t1 = view.notification_time_3.text.toString()
                d2 = view.notification_date_2.text.toString()
                t2 = view.notification_time_2.text.toString()

                view.notification_date_3.text = d2
                view.notification_time_3.text = t2
                view.notification_date_2.text = d1
                view.notification_time_2.text = t1

            }

            datetime1 = "${view.notification_date_1.text} ${view.notification_time_1.text}:00"
            datetime2 = "${view.notification_date_2.text} ${view.notification_time_2.text}:00"

            if (convertMillis(datetime2) < convertMillis(datetime1)){
                d1 = view.notification_date_2.text.toString()
                t1 = view.notification_time_2.text.toString()
                d2 = view.notification_date_1.text.toString()
                t2 = view.notification_time_1.text.toString()

                view.notification_date_2.text = d2
                view.notification_time_2.text = t2
                view.notification_date_1.text = d1
                view.notification_time_1.text = t1

            }
        }

        else if (view.notification_device_4.visibility == View.VISIBLE) {
            var datetime1 = "${view.notification_date_1.text} ${view.notification_time_1.text}:00"
            var datetime2 = "${view.notification_date_2.text} ${view.notification_time_2.text}:00"
            var datetime3 = "${view.notification_date_3.text} ${view.notification_time_3.text}:00"
            var datetime4 = "${view.notification_date_4.text} ${view.notification_time_4.text}:00"

            if (convertMillis(datetime4) < convertMillis(datetime3)) {
                d1 = view.notification_date_4.text.toString()
                t1 = view.notification_time_4.text.toString()
                d2 = view.notification_date_3.text.toString()
                t2 = view.notification_time_3.text.toString()

                view.notification_date_4.text = d2
                view.notification_time_4.text = t2
                view.notification_date_3.text = d1
                view.notification_time_3.text = t1

            }

            datetime2 = "${view.notification_date_2.text} ${view.notification_time_2.text}:00"
            datetime3 = "${view.notification_date_3.text} ${view.notification_time_3.text}:00"

            if (convertMillis(datetime3) < convertMillis(datetime2)){
                d1 = view.notification_date_3.text.toString()
                t1 = view.notification_time_3.text.toString()
                d2 = view.notification_date_2.text.toString()
                t2 = view.notification_time_2.text.toString()

                view.notification_date_3.text = d2
                view.notification_time_3.text = t2
                view.notification_date_2.text = d1
                view.notification_time_2.text = t1

            }

            datetime1 = "${view.notification_date_1.text} ${view.notification_time_1.text}:00"
            datetime2 = "${view.notification_date_2.text} ${view.notification_time_2.text}:00"

            if (convertMillis(datetime2) < convertMillis(datetime1)){
                d1 = view.notification_date_2.text.toString()
                t1 = view.notification_time_2.text.toString()
                d2 = view.notification_date_1.text.toString()
                t2 = view.notification_time_1.text.toString()

                view.notification_date_2.text = d2
                view.notification_time_2.text = t2
                view.notification_date_1.text = d1
                view.notification_time_1.text = t1

            }

        }

        else if (view.notification_device_3.visibility == View.VISIBLE) {
            var datetime1 = "${view.notification_date_1.text} ${view.notification_time_1.text}:00"
            var datetime2 = "${view.notification_date_2.text} ${view.notification_time_2.text}:00"
            var datetime3 = "${view.notification_date_3.text} ${view.notification_time_3.text}:00"

            if (convertMillis(datetime3) < convertMillis(datetime2)){
                d1 = view.notification_date_3.text.toString()
                t1 = view.notification_time_3.text.toString()
                d2 = view.notification_date_2.text.toString()
                t2 = view.notification_time_2.text.toString()

                view.notification_date_3.text = d2
                view.notification_time_3.text = t2
                view.notification_date_2.text = d1
                view.notification_time_2.text = t1

            }

            datetime1 = "${view.notification_date_1.text} ${view.notification_time_1.text}:00"
            datetime2 = "${view.notification_date_2.text} ${view.notification_time_2.text}:00"


            if (convertMillis(datetime2) < convertMillis(datetime1)){
                d1 = view.notification_date_2.text.toString()
                t1 = view.notification_time_2.text.toString()
                d2 = view.notification_date_1.text.toString()
                t2 = view.notification_time_1.text.toString()

                view.notification_date_2.text = d2
                view.notification_time_2.text = t2
                view.notification_date_1.text = d1
                view.notification_time_1.text = t1

            }
        }

        else if (view.notification_device_2.visibility == View.VISIBLE) {
            val datetime1 = "${view.notification_date_1.text} ${view.notification_time_1.text}:00"
            val datetime2 = "${view.notification_date_2.text} ${view.notification_time_2.text}:00"

            if (convertMillis(datetime2) < convertMillis(datetime1)){
                d1 = view.notification_date_2.text.toString()
                t1 = view.notification_time_2.text.toString()
                d2 = view.notification_date_1.text.toString()
                t2 = view.notification_time_1.text.toString()

                view.notification_date_2.text = d2
                view.notification_time_2.text = t2
                view.notification_date_1.text = d1
                view.notification_time_1.text = t1

            }
        }
    }

    private fun organizeListTime(t1:TextView,t2:TextView,t3:TextView,t4:TextView,t5:TextView) :MutableList<Any>{
        val listTime:List<Any> = arrayListOf(t1.text,t2.text,t3.text,t4.text,t5.text)
        println(listTime)
        var finalListTime:MutableList<Any> = arrayListOf()

        for (item in listTime){
            if (item != ""){
                finalListTime.add(item)
            }
        }

        while (finalListTime.size < 5){
            finalListTime.add("null")
        }

        return finalListTime
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun roundTime(): LocalDateTime {

        var date = LocalDateTime.now(ZoneId.of("Asia/Bangkok"))

        if (date.minute >= 45 ){ return LocalDateTime.now().plusHours(1).withMinute(0) }
        else if (date.minute >= 0 && date.minute < 15){ return LocalDateTime.now().withMinute(15) }
        else if (date.minute >= 15 && date.minute <30){ return LocalDateTime.now().withMinute(30) }
        else if (date.minute >= 30 && date.minute < 45){ return LocalDateTime.now().withMinute(45) }
        else{ return date }
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

            println("Timevalue ${timeValue}")
            println("TS : ${TS}")
            addNotifications(view,timeValue,TS)
        }
    }

    inner class MinMaxFilter():InputFilter {
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

    private fun convertDateTime(timeInMillis: Long): String =
            DateFormat.format("dd/MM/yyyy HH:mm",timeInMillis).toString()

    private fun convertDate(timeInMillis: Long): String =
            DateFormat.format("dd/MM/yyyy",timeInMillis).toString()

    private fun convertTime(timeInMillis: Long): String =
            DateFormat.format("HH:mm",timeInMillis).toString()

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addNotifications(view: View, s: String, ts:String){
        val date = view.date_begin_event_add.text.toString()
        val time = view.time_begin_event_add.text.toString()

        val datetime = "$date $time:00"
        var Date = convertMillis(datetime)


        if (ts == "M"){
            Date = convertMillis(datetime) - TimeUnit.MINUTES.toMillis(s.toLong())

        }
        else if (ts == "H"){
            Date = convertMillis(datetime) - TimeUnit.HOURS.toMillis(s.toLong())

        }
        else if (ts == "D"){
            Date = convertMillis(datetime) - TimeUnit.DAYS.toMillis(s.toLong())
        }
        else if (ts == "W"){

            val rs = s.toInt() * 7

            Date = convertMillis(datetime) - TimeUnit.DAYS.toMillis(rs.toLong())
        }

        val checkDatetime = convertDateTime(Date)
        val notificationDate = convertDate(Date)
        val notificationTime = convertTime(Date)


        if (checkDatetime != "${view.notification_date_1.text} ${view.notification_time_1.text}" &&
                checkDatetime != "${view.notification_date_2.text} ${view.notification_time_2.text}" &&
                checkDatetime != "${view.notification_date_3.text} ${view.notification_time_3.text}" &&
                checkDatetime != "${view.notification_date_4.text} ${view.notification_time_4}" &&
                checkDatetime != "${view.notification_date_5.text} ${view.notification_time_5.text}"){

            println(checkDatetime)
            println("${view.notification_date_1.text} ${view.notification_time_1.text}")

            if (view.notification_device_1.visibility == View.GONE) {
                view.notification_device_1.visibility = View.VISIBLE
                view.notification_date_1.text = notificationDate
                view.notification_time_1.text = notificationTime
            } else if (view.notification_device_2.visibility == View.GONE) {
                view.notification_device_2.visibility = View.VISIBLE
                view.notification_date_2.text = notificationDate
                view.notification_time_2.text = notificationTime
            } else if (view.notification_device_3.visibility == View.GONE) {
                view.notification_device_3.visibility = View.VISIBLE
                view.notification_date_3.text = notificationDate
                view.notification_time_3.text = notificationTime
            } else if (view.notification_device_4.visibility == View.GONE) {
                view.notification_device_4.visibility = View.VISIBLE
                view.notification_date_4.text = notificationDate
                view.notification_time_4.text = notificationTime
            } else if (view.notification_device_5.visibility == View.GONE) {
                view.notification_device_5.visibility = View.VISIBLE
                view.notification_date_5.text = notificationDate
                view.notification_time_5.text = notificationTime
            }
            if (view.notification_device_5.visibility == View.VISIBLE && view.notification_device_4.visibility == View.VISIBLE
                    && view.notification_device_3.visibility == View.VISIBLE && view.notification_device_2.visibility == View.VISIBLE
                    && view.notification_device_1.visibility == View.VISIBLE) {
                view.add_notification_device.visibility = View.GONE
            }

            sortTime(view)



        }

    }

    private fun setVisibility(view:View){
        view.notification_device_5.visibility = View.GONE
        view.notification_device_4.visibility = View.GONE
        view.notification_device_3.visibility = View.GONE
        view.notification_device_2.visibility = View.GONE
        view.notification_device_1.visibility = View.GONE
        view.notification_date_1.text = ""
        view.notification_date_2.text = ""
        view.notification_date_3.text = ""
        view.notification_date_4.text = ""
        view.notification_date_5.text = ""
    }











}