package com.example.project.Setting.SubjectsManage

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.MainActivity
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_event_notification_add.view.*
import kotlinx.android.synthetic.main.fragment_subjects_manage.view.*
import kotlinx.android.synthetic.main.fragment_subjects_manage_add.*
import kotlinx.android.synthetic.main.fragment_subjects_manage_add.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class SubjectsManageAdd : Fragment() {

    private lateinit var mSubjectViewModel: SubjectViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_subjects_manage_add, container, false)

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)


        view.cancel_button_update.setOnClickListener {
            val action = SubjectsManageAddDirections.actionSubjectsManageAddToSubjectsManageNav()
            view.findNavController().navigate(action)
        }

        view.date_mid_manager_update.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(roundTime()))
        view.date_final_manager_update.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(roundTime().plusMonths(2)))

        view.time_mid_begin_manager_update.setText(DateTimeFormatter.ofPattern("HH:mm").format(roundTime()))
        view.time_mid_end_manager_update.setText(DateTimeFormatter.ofPattern("HH:mm").format(roundTime().plusHours(1)))

        view.time_final_begin_manager_update.setText(DateTimeFormatter.ofPattern("HH:mm").format(roundTime()))
        view.time_final_end_manager_update.setText(DateTimeFormatter.ofPattern("HH:mm").format(roundTime().plusHours(1)))

        view.admit_button_update.setOnClickListener {
            insertDataToDatabase()
        }

        view.date_mid_manager_update.setOnClickListener {
            getDate(view.date_mid_manager_update)
        }

        view.date_final_manager_update.setOnClickListener {
            getDate(view.date_final_manager_update)
        }

        view.time_mid_begin_manager_update.setOnClickListener {
            getTime(view.time_mid_begin_manager_update)
        }

        view.time_mid_end_manager_update.setOnClickListener {
            getTime(view.time_mid_end_manager_update)
        }

        view.time_final_begin_manager_update.setOnClickListener {
            getTime(view.time_final_begin_manager_update)
        }

        view.time_final_end_manager_update.setOnClickListener {
            getTime(view.time_final_end_manager_update)
        }

        view.building_mid_manager_update.setOnClickListener {
            setBuilding(view.building_mid_manager_update)
        }

        view.building_final_manager_update.setOnClickListener {
            setBuilding(view.building_final_manager_update)
        }

        view.room_mid_manager_update.setOnClickListener {
            setRoom(view.building_mid_manager_update,view.room_mid_manager_update)
        }

        view.room_final_manager_update.setOnClickListener {
            setRoom(view.building_final_manager_update,view.room_final_manager_update)
        }



        return view
    }

    private fun insertDataToDatabase(){
        val sid = code_manager_update.text.toString()
        val name = name_manager_update.text.toString()
        val midDate = date_mid_manager_update.text.toString()
        val finalDate = date_final_manager_update.text.toString()
        val midTimeBegin = time_mid_begin_manager_update.text.toString()
        val midTimeEnd = time_mid_end_manager_update.text.toString()
        val finalTimeBegin = time_final_begin_manager_update.text.toString()
        val finalTimeEnd = time_final_end_manager_update.text.toString()
        val midBuilding = building_mid_manager_update.text.toString()
        val finalBuilding = building_final_manager_update.text.toString()
        val midRoom = room_mid_manager_update.text.toString()
        val finalRoom = room_final_manager_update.text.toString()
        val id:Int = ("1".toString() + sid).toString().toInt()

        if (inputCheck(sid,name,midDate,finalDate,midTimeBegin,midTimeEnd,finalTimeBegin,finalTimeEnd,midBuilding,finalBuilding,midRoom,finalRoom)){
            val subject = Subject(id,sid,name,midBuilding,midRoom,midTimeBegin,midTimeEnd,midDate,finalBuilding,finalRoom,finalTimeBegin,finalTimeEnd,finalDate)

            mSubjectViewModel.addSubject(subject)

            Toast.makeText(requireContext(),"Successfully add!",Toast.LENGTH_SHORT).show()

            val action = SubjectsManageAddDirections.actionSubjectsManageAddToSubjectsManageNav()
            findNavController().navigate(action)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields.",Toast.LENGTH_SHORT).show()


        }
    }

    private fun inputCheck(sid:String,name:String,MDate:String,FDate:String,MTB:String,MTE:String,FTB:String,FTE:String,MBuilding:String,FBuilding:String,MRoom:String,FRoom:String):Boolean{

        return  !(TextUtils.isEmpty(sid)||TextUtils.isEmpty(name)||MDate.equals("")||FDate.equals("")||MTB.equals("")||MTE.equals("")
                ||FTB.equals("")||FTE.equals("")||MBuilding.equals("")||FBuilding.equals("")||MRoom.equals("")||FRoom.equals(""))

    }
    private fun getTime(tv: TextView){
        val cal = Calendar.getInstance()
        val timeSet = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY,hour)
            cal.set(Calendar.MINUTE,minute)

            tv.text = SimpleDateFormat("HH:mm").format(cal.time).toString()
        }
        TimePickerDialog(requireContext(), AlertDialog.THEME_HOLO_LIGHT,timeSet,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
    }

    private fun getDate(tv: TextView){
        val cal = Calendar.getInstance()
        val dpd = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.YEAR,year)



            tv.text = SimpleDateFormat("dd/MM/yyyy").format(cal.time).toString()


        }

        DatePickerDialog(requireContext(), AlertDialog.THEME_HOLO_LIGHT,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()

    }

    private fun setBuilding(tv: TextView){

        val ListBuilding = arrayOf("81","89","88","87")

        val BBuilder = AlertDialog.Builder(requireContext())

        BBuilder.setTitle("Choose Building")

        BBuilder.setSingleChoiceItems(ListBuilding,-1){dialog,i->

            tv.setText(ListBuilding[i])

            dialog.dismiss()
        }

        BBuilder.show()
    }

    private fun setRoom(tv1: TextView, tv2: TextView){

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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun roundTime(): LocalDateTime {

        var date = LocalDateTime.now(ZoneId.of("Asia/Bangkok"))

        if (date.minute >= 45 ){ return LocalDateTime.now().plusHours(1).withMinute(0) }
        else if (date.minute >= 0 && date.minute < 15){ return LocalDateTime.now().withMinute(15) }
        else if (date.minute >= 15 && date.minute <30){ return LocalDateTime.now().withMinute(30) }
        else if (date.minute >= 30 && date.minute < 45){ return LocalDateTime.now().withMinute(45) }
        else{ return date }
    }



}