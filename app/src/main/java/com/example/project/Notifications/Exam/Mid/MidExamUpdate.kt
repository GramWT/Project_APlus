package com.example.project.Notifications.Exam.Mid

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.MainActivity
import com.example.project.Notifications.Exam.ExamNotification
import com.example.project.R
import com.example.project.databinding.FragmentSubjectTabBinding
import kotlinx.android.synthetic.main.fragment_mid_exam_update.*
import kotlinx.android.synthetic.main.fragment_mid_exam_update.view.*
import kotlinx.android.synthetic.main.fragment_subject_tab.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log


class MidExamUpdate : Fragment() {

    private val args by navArgs<MidExamUpdateArgs>()
    private lateinit var mSubjectViewModel:SubjectViewModel
    private lateinit var mAlarmService:AlarmService




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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view  = inflater.inflate(R.layout.fragment_mid_exam_update, container, false)
        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mAlarmService = AlarmService(requireContext())

        view.title_update_view.setText(args.subject.Name)
        view.date_update_view.setText(args.subject.mid_date)
        view.begin_time_update_view.setText(args.subject.mid_begin_time)
        view.end_time_update_view.setText(args.subject.mid_end_time)
        view.building_update_view.setText(args.subject.mid_building)
        view.room_update_view.setText(args.subject.mid_room)

        view.save_button_update_view.setOnClickListener {
            val action = MidExamUpdateDirections.actionMidExamUpdateToMidExamNotification()
            val data = view.date_update_view.text.toString() + " " + view.begin_time_update_view.text.toString() + ":00"
            updateItem()
            setAlarm(data,args.subject.id)
            Toast.makeText(requireContext(),"Update Successfully",Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(action)
        }

        view.cancel_button_update_view.setOnClickListener {
            val action = MidExamUpdateDirections.actionMidExamUpdateToMidExamNotification()
            view.findNavController().navigate(action)
        }


        view.begin_time_update_view.setOnClickListener {
            getTime(view.begin_time_update_view)
        }

        view.end_time_update_view.setOnClickListener {
            getTime(view.end_time_update_view)
        }

        view.date_update_view.setOnClickListener {
            getDate(view.date_update_view)
        }

        view.building_update_view.setOnClickListener {
            setBuilding(view.building_update_view)
        }

        view.room_update_view.setOnClickListener {
            setRoom(view.building_update_view,view.room_update_view)
        }


        return view
    }

    private fun updateItem(){
        val subject = Subject(args.subject.id,args.subject.sid,args.subject.Name,building_update_view.text.toString(),room_update_view.text.toString(),begin_time_update_view.text.toString(),
        end_time_update_view.text.toString(),date_update_view.text.toString(),args.subject.final_building,args.subject.final_room,args.subject.final_begin_time,args.subject.final_begin_time,
        args.subject.final_date)
        mSubjectViewModel.updateSubject(subject)
    }

    private fun getTime(tv:TextView){
        val cal = Calendar.getInstance()
        val timeSet = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY,hour)
            cal.set(Calendar.MINUTE,minute)

            tv.text = SimpleDateFormat("HH:mm").format(cal.time).toString()
        }
        TimePickerDialog(requireContext(),AlertDialog.THEME_HOLO_LIGHT,timeSet,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
    }

    private fun getDate(tv:TextView){
        val cal = Calendar.getInstance()
        val dpd = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.YEAR,year)



            tv.text = SimpleDateFormat("dd/MM/yyyy").format(cal.time).toString()


        }

        DatePickerDialog(requireContext(),AlertDialog.THEME_HOLO_LIGHT,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()

    }

    private fun setBuilding(tv:TextView){

        val ListBuilding = arrayOf("81","89","88","87")

        val BBuilder = AlertDialog.Builder(requireContext())

        BBuilder.setTitle("Choose Building")

        BBuilder.setSingleChoiceItems(ListBuilding,-1){dialog,i->

            tv.setText(ListBuilding[i])

            dialog.dismiss()
        }

        BBuilder.show()
    }

    private fun setRoom(tv1:TextView,tv2:TextView){

        var ListRoom = arrayOf("0")

        when(tv1.text){
            "81" -> ListRoom = arrayOf("506A","506B")
            "89" -> ListRoom = arrayOf("405","404")
        }

        val RBuilder = AlertDialog.Builder(requireContext())

        RBuilder.setTitle("Choose Room")

        RBuilder.setSingleChoiceItems(ListRoom,-1){dialog,i ->

            tv2.setText(ListRoom[i])
            dialog.dismiss()

        }
        RBuilder.show()
    }

    private fun setAlarm(date:String,rq:Int){
        mAlarmService.setExactAlarm(convertMillis(date),rq)
    }

    private fun convertMillis(data: String):Long{
        var sp = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        var date:Date = sp.parse(data)
        var millis:Long = date.time

        return millis
    }

}