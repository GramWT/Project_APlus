package com.example.project.Notifications.Event

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.DataBase.model.Event
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.viewmodel.EventViewModel
import com.example.project.MainActivity
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_event_notification_add.view.*
import kotlinx.android.synthetic.main.fragment_event_notification_update.*
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.*
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.state_image
import kotlinx.android.synthetic.main.fragment_subjects_manage_update.*
import java.text.SimpleDateFormat
import java.util.*

class EventNotificationUpdate : Fragment() {

    private val args by navArgs<EventNotificationUpdateArgs>()
    private lateinit var mEventViewModel: EventViewModel





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_event_notification_update, container, false)

        mEventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        val currentItem = args.event

        setNotification(view)

        view.title_event_update.setText(args.event.title)
        view.date_begin_event_update.setText(args.event.date_begin)
        view.date_end_event_update.setText(args.event.date_end)
        view.time_begin_event_update.setText(args.event.time_begin)
        view.time_end_event_update.setText(args.event.time_end)
        view.location_event_update.setText(args.event.location)

        view.time_1.text = args.event.time_notification_1
        view.time_2.text = args.event.time_notification_2
        view.time_3.text = args.event.time_notification_3
        view.time_4.text = args.event.time_notification_4
        view.time_5.text = args.event.time_notification_5

        view.description_event_update.setText(args.event.description)
        view.state_event_update.setText(args.event.state)
        setState(args.event.state,view.state_image)

        setShowNotification(view)

        view.cancel_button_event_update.setOnClickListener {
            val action = EventNotificationUpdateDirections.actionEventNotificationUpdateToEventNotificationView(currentItem)
            findNavController().navigate(action)
        }

        view.save_button_event_update.setOnClickListener {
            updateItem()
            val event = Event(args.event.id,view.title_event_update.text.toString(),view.date_begin_event_update.text.toString(),view.date_end_event_update.text.toString(),view.time_begin_event_update.text.toString(),
                    view.time_end_event_update.text.toString(),view.state_event_update.text.toString(),view.description_event_update.text.toString(),view.time_1.text.toString(),
                    "0","0","0","0",view.location_event_update.text.toString())
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

    private fun updateItem(){
        val event = Event(args.event.id,title_event_update.text.toString(),date_begin_event_update.text.toString(),date_end_event_update.text.toString(),time_begin_event_update.text.toString(),
        time_end_event_update.text.toString(),state_event_update.text.toString(),description_event_update.text.toString(),time_1.text.toString(),
        "0","0","0","0",location_event_update.text.toString())
        mEventViewModel.updateEvent(event)
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
        if (view.time_1.text != "null"){
            view.notification_1.visibility = View.VISIBLE
        }
        if (view.time_2.text != "null"){
            view.notification_2.visibility = View.VISIBLE
        }
        if (view.time_3.text != "null"){
            view.notification_3.visibility = View.VISIBLE
        }
        if (view.time_4.text != "null"){
            view.notification_4.visibility = View.VISIBLE
        }
        if (view.time_5.text != "null"){
            view.notification_5.visibility = View.VISIBLE
        }
    }


}