package com.example.project.Notifications.Event

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.isInvisible
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.project.DataBase.model.Event
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.viewmodel.EventViewModel
import com.example.project.MainActivity
import com.example.project.R
import com.example.project.Setting.SubjectsManage.SubjectsManageAddDirections
import com.example.project.databinding.FieldNotificationBinding
import kotlinx.android.synthetic.main.field_notification.view.*
import kotlinx.android.synthetic.main.fragment_event_notification_add.*
import kotlinx.android.synthetic.main.fragment_event_notification_add.view.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class EventNotificationAdd : Fragment() {

    private lateinit var mEventViewModel:EventViewModel
    private lateinit var binding: FieldNotificationBinding

    private var parentLinearLayout: LinearLayout? = null

    override fun onResume() {

        super.onResume()
        val a = activity as MainActivity
        a.hideBottomNav()
    }

    override fun onStop() {
        super.onStop()
        val a = activity as MainActivity
        a.showBottomNav()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.fragment_event_notification_add, container, false)

        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)


        view.date_begin_event_add.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(roundTime()))

        view.date_end_event_add.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(roundTime().plusHours(1)))

        view.time_begin_event_add.setText(DateTimeFormatter.ofPattern("HH:mm").format(roundTime()))

        view.time_end_event_add.setText(DateTimeFormatter.ofPattern("HH:mm").format(roundTime().plusHours(1)))

        view.notification_event_add.setOnClickListener {
            onAddField(view.notification_field,view.notification_event_add)

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
            setState(view.state_event_add,view.state_color)
        }

        return view
    }

    private fun insertDataToDatabase(){
        val title = title_event_add.text.toString()
        val dateBegin = date_begin_event_add.text.toString()
        val dateEnd = date_end_event_add.text.toString()
        val timeBegin = time_begin_event_add.text.toString()
        val timeEnd = time_end_event_add.text.toString()
        val state = state_event_add.text.toString()
        val description = description_event_add.text.toString()

        if (inputCheck(title,dateBegin,dateEnd,timeBegin,timeEnd,state,description)){
            val event = Event(0,title,dateBegin,dateEnd,timeBegin,timeEnd,state,description,"0","0","0","0","0")

            mEventViewModel.addEvent(event)

            Toast.makeText(requireContext(),"Successfully add!", Toast.LENGTH_SHORT).show()

            val action = EventNotificationAddDirections.actionEventNotificationAddToEventNotification()
            findNavController().navigate(action)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields.", Toast.LENGTH_SHORT).show()


        }
    }

    private fun inputCheck(title:String, dateBegin:String, dateEnd:String, timeBegin:String, timeEnd:String, state:String, description:String):Boolean{

        return  !(TextUtils.isEmpty(title)|| TextUtils.isEmpty(dateBegin)||dateEnd.equals("")||timeBegin.equals("")||timeEnd.equals("")||state.equals("")
                ||description.equals(""))

    }

    private fun setState(tv: TextView,im: ImageView){

        val ListState = arrayOf("High","Normal","Low","Default")

        val BBuilder = AlertDialog.Builder(requireContext())

        BBuilder.setTitle("Choose State")

        BBuilder.setSingleChoiceItems(ListState,-1){dialog,i->

            tv.setText(ListState[i])
            dialog.dismiss()
            when(ListState[i]){
                "Default" -> im.setImageResource(R.drawable.ic_default_color_circle)
                "High" -> im.setImageResource(R.drawable.ic_high_color_circle)
                "Normal" -> im.setImageResource(R.drawable.ic_normal_color_circle)
                "Low" -> im.setImageResource(R.drawable.ic_low_color_circle)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun roundTime(): LocalDateTime {

        var date = LocalDateTime.now(ZoneId.of("Asia/Bangkok"))

        if (date.minute >= 45 ){ return LocalDateTime.now().plusHours(1).withMinute(0) }
        else if (date.minute >= 0 && date.minute < 15){ return LocalDateTime.now().withMinute(15) }
        else if (date.minute >= 15 && date.minute <30){ return LocalDateTime.now().withMinute(30) }
        else if (date.minute >= 30 && date.minute < 45){ return LocalDateTime.now().withMinute(45) }
        else{ return date }
    }

    private fun onAddField(AddLayout:LinearLayout,NotificationButton:TextView){
        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var rowView:View = inflater.inflate(R.layout.field_notification,null,false)

        if (AddLayout.size <= 5){
            rowView.id = AddLayout.size
            rowView.time_notification_field.id = "1${AddLayout.size}".toInt()
            rowView.delete_button_field.id = "2${AddLayout.size}".toInt()

            println(AddLayout.size)
            AddLayout.addView(rowView,AddLayout.childCount -1)
        }

        if (AddLayout.size == 6){
            AddLayout.removeView(NotificationButton)

        }

    }
}