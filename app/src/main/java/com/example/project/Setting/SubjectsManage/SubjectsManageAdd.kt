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
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.EventCalendar
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.R
import kotlinx.android.synthetic.main.dialog_building_select.view.*
import kotlinx.android.synthetic.main.fragment_mid_exam_update.view.*
import kotlinx.android.synthetic.main.fragment_subjects_manage_add.*
import kotlinx.android.synthetic.main.fragment_subjects_manage_add.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class SubjectsManageAdd : Fragment() {

    private lateinit var mSubjectViewModel: SubjectViewModel
    private lateinit var mEventCalendar:EventCalendarViewModel
    private lateinit var mAlarmService: AlarmService

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_subjects_manage_add, container, false)

        mAlarmService = AlarmService(requireContext())


        val dateM = LocalDateTime.now()
        val dayM = dateM.dayOfMonth
        val monthM = dateM.monthValue - 1
        val yearM = dateM.year

        val dateF = LocalDateTime.now().plusMonths(2)
        val dayF = dateF.dayOfMonth
        val monthF = dateF.monthValue - 1
        val yearF = dateF.year

        var eventDateMid:MutableList<Int> = arrayListOf(dayM,monthM,yearM)
        var eventDateFinal:MutableList<Int> = arrayListOf(dayF,monthF,yearF)


        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)

        mEventCalendar = ViewModelProvider(this).get(EventCalendarViewModel::class.java)


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
            insertDataToDatabase(eventDateMid,eventDateFinal)
        }

        view.date_mid_manager_update.setOnClickListener {
            println(eventDateMid)
            eventDateMid = arrayListOf()
            getDate(view.date_mid_manager_update,eventDateMid)
        }

        view.date_final_manager_update.setOnClickListener {
            println(eventDateFinal)
            eventDateFinal = arrayListOf()
            getDate(view.date_final_manager_update,eventDateFinal)
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
            dialogBuilding(view.building_mid_manager_update)
        }

        view.building_final_manager_update.setOnClickListener {
            dialogBuilding(view.building_final_manager_update)
        }

        view.room_mid_manager_update.setOnClickListener {
            setRoom(view.building_mid_manager_update,view.room_mid_manager_update)
        }

        view.room_final_manager_update.setOnClickListener {
            setRoom(view.building_final_manager_update,view.room_final_manager_update)
        }



        return view
    }

    private fun insertDataToDatabase(eventMid:MutableList<Int>,eventFinal:MutableList<Int>){
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
        val id:Int = sid.toInt()
        val mid:Int = sid.toInt() + 1
        val final:Int = sid.toInt() + 2

        val setId:Int = sid.substring(0,3).toInt() + sid.substring(4,7).toInt()
        val idMid:Int = "1${setId.toString()}".toInt()
        val idFinal:Int = "2${setId.toString()}".toInt()

        println("My ID : ${setId}")

        if (inputCheck(sid,name,midDate,finalDate,midTimeBegin,midTimeEnd,finalTimeBegin,finalTimeEnd,midBuilding,finalBuilding,midRoom,finalRoom)){
            val subject = Subject(setId,sid,name,midBuilding,midRoom,midTimeBegin,midTimeEnd,midDate,finalBuilding,finalRoom,finalTimeBegin,finalTimeEnd,finalDate)



            val eventMid = EventCalendar(mid,1,eventMid[0],eventMid[1],eventMid[2],name)

            val eventFinal = EventCalendar(final,2,eventFinal[0],eventFinal[1],eventFinal[2],name)

            val midData = "$midDate $midTimeBegin:00"
            val finalData = "$finalDate $finalTimeBegin:00"
            mSubjectViewModel.addSubject(subject)

            mEventCalendar.addEventCalendar(eventMid)
            mEventCalendar.addEventCalendar(eventFinal)

            setAlarm(midData,idMid,sid)
            setAlarm(finalData,idFinal,sid)


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
        TimePickerDialog(requireContext(), AlertDialog.THEME_HOLO_DARK,timeSet,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
    }

    private fun getDate(tv: TextView,date:MutableList<Int>){

        val cal = Calendar.getInstance()
        val dpd = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            cal.set(Calendar.MONTH,month)
            cal.set(Calendar.YEAR,year)

            date.add(dayOfMonth)
            date.add(month)
            date.add(year)



            tv.text = SimpleDateFormat("dd/MM/yyyy").format(cal.time).toString()


        }

        DatePickerDialog(requireContext(), AlertDialog.THEME_DEVICE_DEFAULT_DARK,dpd,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()

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

    private fun setAlarm(date:String,rq:Int,SID:String){
        mAlarmService.setExactAlarm(convertMillis(date),rq,SID)
    }

    private fun convertMillis(data: String):Long{
        var sp = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        var date:Date = sp.parse(data)
        var millis:Long = date.time

        return millis
    }

    private fun dialogBuilding(tv:TextView){
        val selectBuilding = LayoutInflater.from(context).inflate(R.layout.dialog_building_select,null)
        val mBuilder = AlertDialog.Builder(context)
                .setView(selectBuilding)

        val mAlert = mBuilder.show()

        selectBuilding.radio_group_building.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radio_building_81){
                tv.setText("81")
            }
            if (checkedId == R.id.radio_building_82){
                tv.setText("82")
            }
            if (checkedId == R.id.radio_building_83){
                tv.setText("83")
            }
            if (checkedId == R.id.radio_building_84){
                tv.setText("84")
            }
            if (checkedId == R.id.radio_building_85){
                tv.setText("85")
            }
            if (checkedId == R.id.radio_building_86){
                tv.setText("86")
            }
            if (checkedId == R.id.radio_building_88){
                tv.setText("88")
            }
            if (checkedId == R.id.radio_building_89){
                tv.setText("89")
            }
            mAlert.dismiss()

        }






    }



}