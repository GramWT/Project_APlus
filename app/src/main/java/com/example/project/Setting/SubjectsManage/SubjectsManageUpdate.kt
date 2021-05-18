package com.example.project.Setting.SubjectsManage

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.EventCalendar
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.R
import kotlinx.android.synthetic.main.dialog_building_select.view.*
import kotlinx.android.synthetic.main.fragment_subjects_manage_update.*
import kotlinx.android.synthetic.main.fragment_subjects_manage_update.view.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class SubjectsManageUpdate : Fragment() {

    private val args by navArgs<SubjectsManageUpdateArgs>()
    private lateinit var mSubjectViewModel: SubjectViewModel
    private lateinit var mEventCalendarViewModel: EventCalendarViewModel
    private lateinit var mAlarmService: AlarmService


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_subjects_manage_update, container, false)
        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mEventCalendarViewModel = ViewModelProvider(this).get(EventCalendarViewModel::class.java)
        mAlarmService = AlarmService(requireContext())

        view.code_manager_update.setText(args.subject.sid)
        view.name_manager_update.setText(args.subject.Name)
        view.date_mid_manager_update.setText(args.subject.mid_date)
        view.date_final_manager_update.setText(args.subject.final_date)
        view.building_mid_manager_update.setText(args.subject.mid_building)
        view.building_final_manager_update.setText(args.subject.final_building)
        view.room_mid_manager_update.setText(args.subject.mid_room)
        view.room_final_manager_update.setText(args.subject.final_room)
        view.time_mid_begin_manager_update.setText(args.subject.mid_begin_time)
        view.time_mid_end_manager_update.setText(args.subject.mid_end_time)
        view.time_final_begin_manager_update.setText(args.subject.final_begin_time)
        view.time_final_end_manager_update.setText(args.subject.final_end_time)

        view.cancel_button_update.setOnClickListener {
            val action =
                SubjectsManageUpdateDirections.actionSubjectsManageUpdateToSubjectsManageNav()
            view.findNavController().navigate(action)
        }

        view.admit_button_update.setOnClickListener {
            val action =
                SubjectsManageUpdateDirections.actionSubjectsManageUpdateToSubjectsManageNav()
            updateItem()
            Toast.makeText(requireContext(), "Update Successfully", Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(action)
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
            dialogBuilding(view.building_mid_manager_update)
        }

        view.building_final_manager_update.setOnClickListener {
            dialogBuilding(view.building_final_manager_update)
        }


        view.deleteButton.setOnClickListener {
            deleteSubject()
            val idMid: Int = "1${args.subject.id}".toInt()
            val idFinal: Int = "2${args.subject.id}".toInt()
            mAlarmService.cancelExamAlarm(idMid)
            mAlarmService.cancelExamAlarm(idFinal)
        }

        return view
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateItem() {
        val subject = Subject(
            args.subject.id,
            code_manager_update.text.toString(),
            name_manager_update.text.toString(),
            building_mid_manager_update.text.toString(),
            room_mid_manager_update.text.toString(),
            time_mid_begin_manager_update.text.toString(),
            time_mid_end_manager_update.text.toString(),
            date_mid_manager_update.text.toString(),
            building_final_manager_update.text.toString(),
            room_final_manager_update.text.toString(),
            time_final_begin_manager_update.text.toString(),
            time_final_end_manager_update.text.toString(),
            date_final_manager_update.text.toString()
        )
        mSubjectViewModel.updateSubject(subject)


        val midAlarmId: Int = "1${args.subject.id}".toInt()
        val finalAlarmId: Int = "2${args.subject.id}".toInt()

        val mC = convertDate(date_mid_manager_update.text.toString())
        val fC = convertDate(date_final_manager_update.text.toString())

        val eventMid =
            EventCalendar(midAlarmId, 1, mC.dayOfMonth, mC.monthValue - 1, mC.year, args.subject.Name)
        val eventFinal =
            EventCalendar(finalAlarmId, 1, fC.dayOfMonth, fC.monthValue - 1, fC.year, args.subject.Name)

        val sid = code_manager_update.text.toString()


        val midData = "${date_mid_manager_update.text} ${time_mid_begin_manager_update.text}:00"
        val finalData =
            "${date_final_manager_update.text} ${time_final_begin_manager_update.text}:00"

        setAlarm(midData, midAlarmId, sid, building_mid_manager_update.text.toString())
        setAlarm(finalData, finalAlarmId, sid, building_final_manager_update.text.toString())

        mEventCalendarViewModel.updateEventCalendar(eventMid)
        mEventCalendarViewModel.updateEventCalendar(eventFinal)
    }

    private fun setAlarm(date: String, rq: Int, SID: String, buildingNo: String) {
        mAlarmService.setExamAlarm(convertMillis(date), rq, SID, buildingNo)
    }

    private fun convertMillis(data: String): Long {
        var sp = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        var date: Date = sp.parse(data)
        var millis: Long = date.time

        return millis
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
            cal.get(Calendar.MINUTE),
            true
        ).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertDate(string: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH)
        val date = LocalDate.parse(string, formatter)
        return date
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
            requireContext(),
            AlertDialog.THEME_DEVICE_DEFAULT_DARK,
            dpd,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()

    }


    private fun deleteSubject() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _ ->

            val mid: Int = "1${args.subject.id}".toInt()
            val final: Int = "2${args.subject.id}".toInt()

            mSubjectViewModel.deleteSubject(args.subject)
            mEventCalendarViewModel.deleteById(mid)
            mEventCalendarViewModel.deleteById(final)
            Toast.makeText(requireContext(), "Successfully deleted ", Toast.LENGTH_SHORT).show()
            val action =
                SubjectsManageUpdateDirections.actionSubjectsManageUpdateToSubjectsManageNav()
            findNavController().navigate(action)

        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ?")
        builder.setMessage("Are you sure you want to delete?")
        builder.show()
    }

    private fun dialogBuilding(tv: TextView) {
        val selectBuilding =
            LayoutInflater.from(context).inflate(R.layout.dialog_building_select, null)
        val mBuilder = AlertDialog.Builder(context)
            .setView(selectBuilding)

        val mAlert = mBuilder.show()

        selectBuilding.radio_group_building.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.radio_building_81) {
                tv.setText("81")
            }
            if (checkedId == R.id.radio_building_82) {
                tv.setText("82")
            }
            if (checkedId == R.id.radio_building_83) {
                tv.setText("83")
            }
            if (checkedId == R.id.radio_building_84) {
                tv.setText("84")
            }
            if (checkedId == R.id.radio_building_85) {
                tv.setText("85")
            }
            if (checkedId == R.id.radio_building_86) {
                tv.setText("86")
            }
            if (checkedId == R.id.radio_building_88) {
                tv.setText("88")
            }
            if (checkedId == R.id.radio_building_89) {
                tv.setText("89")
            }
            mAlert.dismiss()

        }


    }


}