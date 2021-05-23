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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.EventCalendar
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.viewmodel.EventCalendarViewModel
import com.example.project.DataBase.viewmodel.SubjectUploadViewModel
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.R
import com.example.project.databinding.FragmentSubjectUploadAddBinding
import kotlinx.android.synthetic.main.dialog_building_select.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class SubjectUploadAdd : Fragment() {


    private val args by navArgs<SubjectUploadAddArgs>()
    private val binding:FragmentSubjectUploadAddBinding by lazy {
        FragmentSubjectUploadAddBinding.inflate(layoutInflater)
    }

    private lateinit var mSubjectViewModel: SubjectViewModel
    private lateinit var mSubjectUploadModel: SubjectUploadViewModel
    private lateinit var mEventCalendar: EventCalendarViewModel
    private lateinit var mAlarmService: AlarmService
    private lateinit var mSubject: List<Subject>


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAlarmService = AlarmService(requireContext())
        val dateM = LocalDateTime.now()
        val dayM = dateM.dayOfMonth
        val monthM = dateM.monthValue - 1
        val yearM = dateM.year

        val dateF = LocalDateTime.now().plusMonths(2)
        val dayF = dateF.dayOfMonth
        val monthF = dateF.monthValue - 1
        val yearF = dateF.year

        var eventDateMid: MutableList<Int> = arrayListOf(dayM, monthM, yearM)
        var eventDateFinal: MutableList<Int> = arrayListOf(dayF, monthF, yearF)

        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)
        mSubjectUploadModel = ViewModelProvider(this).get(SubjectUploadViewModel::class.java)
        mEventCalendar = ViewModelProvider(this).get(EventCalendarViewModel::class.java)


        binding.cancelButtonUpdate.setOnClickListener {
            val action = SubjectUploadAddDirections.actionSubjectUploadAddToSubjectsManageUploadPDF()
            findNavController().navigate(action)
        }
        binding.dateMidManagerUpdate.text = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(roundTime())
        binding.dateFinalManagerUpdate.text = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(roundTime().plusMonths(2))
        binding.timeMidBeginManagerUpdate.text = DateTimeFormatter.ofPattern("HH:mm").format(roundTime())
        binding.timeMidEndManagerUpdate.text = DateTimeFormatter.ofPattern("HH:mm").format(roundTime().plusHours(1))
        binding.timeFinalBeginManagerUpdate.text = DateTimeFormatter.ofPattern("HH:mm").format(roundTime())
        binding.timeFinalEndManagerUpdate.text = DateTimeFormatter.ofPattern("HH:mm").format(roundTime().plusHours(1))

        binding.dateMidManagerUpdate.setOnClickListener {
            eventDateMid = arrayListOf()
            getDate(binding.dateMidManagerUpdate,eventDateMid)
        }

        binding.dateFinalManagerUpdate.setOnClickListener {
            eventDateFinal = arrayListOf()
            getDate(binding.dateFinalManagerUpdate,eventDateFinal)
        }

        binding.timeMidBeginManagerUpdate.setOnClickListener {
            getTime(binding.timeMidBeginManagerUpdate)
        }

        binding.timeMidEndManagerUpdate.setOnClickListener {
            getTime(binding.timeMidEndManagerUpdate)
        }

        binding.timeFinalBeginManagerUpdate.setOnClickListener {
            getTime(binding.timeFinalBeginManagerUpdate)
        }

        binding.timeFinalEndManagerUpdate.setOnClickListener {
            getTime(binding.timeFinalEndManagerUpdate)
        }

        binding.buildingMidManagerUpdate.setOnClickListener {
            dialogBuilding(binding.buildingMidManagerUpdate)
        }

        binding.buildingFinalManagerUpdate.setOnClickListener {
            dialogBuilding(binding.buildingFinalManagerUpdate)
        }

        binding.admitButtonUpdate.setOnClickListener {
            insertDataToDatabase(eventDateMid,eventDateFinal)
            mSubjectUploadModel.deleteSubject(args.subject)
        }

        binding.codeManagerUpdate.setText(args.subject.sid)
        binding.nameManagerUpdate.setText(args.subject.Name)


        return binding.root





    }

    private fun insertDataToDatabase(eventMid: MutableList<Int>, eventFinal: MutableList<Int>) {
        val sid = binding.codeManagerUpdate.text.toString()
        val name = binding.nameManagerUpdate.text.toString()
        val midDate = binding.dateMidManagerUpdate.text.toString()
        val finalDate = binding.dateFinalManagerUpdate.text.toString()
        val midTimeBegin = binding.timeMidBeginManagerUpdate.text.toString()
        val midTimeEnd = binding.timeMidEndManagerUpdate.text.toString()
        val finalTimeBegin = binding.timeFinalBeginManagerUpdate.text.toString()
        val finalTimeEnd = binding.timeFinalEndManagerUpdate.text.toString()
        val midBuilding = binding.buildingMidManagerUpdate.text.toString()
        val finalBuilding = binding.buildingFinalManagerUpdate.text.toString()
        val midRoom = binding.roomMidManagerUpdate.text.toString()
        val finalRoom = binding.roomFinalManagerUpdate.text.toString()

        val rid = randomId()

        val mid: Int = "1${rid}".toInt()
        val final: Int = "2${rid}".toInt()

        val idMid: Int = "1${rid}".toInt()
        val idFinal: Int = "2${rid}".toInt()


        if (inputCheck(
                sid,
                name,
                midDate,
                finalDate,
                midTimeBegin,
                midTimeEnd,
                finalTimeBegin,
                finalTimeEnd,
                midBuilding,
                finalBuilding,
                midRoom,
                finalRoom
            )
        ) {
            val subject = Subject(
                rid,
                sid,
                name,
                midBuilding,
                midRoom,
                midTimeBegin,
                midTimeEnd,
                midDate,
                finalBuilding,
                finalRoom,
                finalTimeBegin,
                finalTimeEnd,
                finalDate
            )


            val eventMid = EventCalendar(mid, 1, eventMid[0], eventMid[1], eventMid[2], name)

            val eventFinal =
                EventCalendar(final, 1, eventFinal[0], eventFinal[1], eventFinal[2], name)

            val midData = "$midDate $midTimeBegin:00"
            val finalData = "$finalDate $finalTimeBegin:00"
            mSubjectViewModel.addSubject(subject)

            mEventCalendar.addEventCalendar(eventMid)
            mEventCalendar.addEventCalendar(eventFinal)

            setAlarm(midData, idMid, sid, midBuilding)
            setAlarm(finalData, idFinal, sid, finalBuilding)


            Toast.makeText(requireContext(), "Successfully add!", Toast.LENGTH_SHORT).show()

            val action = SubjectUploadAddDirections.actionSubjectUploadAddToSubjectsManageUploadPDF()
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()


        }
    }

    private fun inputCheck(
        sid: String,
        name: String,
        MDate: String,
        FDate: String,
        MTB: String,
        MTE: String,
        FTB: String,
        FTE: String,
        MBuilding: String,
        FBuilding: String,
        MRoom: String,
        FRoom: String
    ): Boolean {

        return !(TextUtils.isEmpty(sid) || TextUtils.isEmpty(name) || MDate.equals("") || FDate.equals(
            ""
        ) || MTB.equals("") || MTE.equals("")
                || FTB.equals("") || FTE.equals("") || MBuilding.equals("") || FBuilding.equals("") || MRoom.equals(
            ""
        ) || FRoom.equals(""))

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

    private fun getDate(tv: TextView, date: MutableList<Int>) {

        val cal = Calendar.getInstance()
        val dpd = DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.YEAR, year)

            date.add(dayOfMonth)
            date.add(month)
            date.add(year)



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

    private fun setAlarm(date: String, rq: Int, SID: String, buildingNo: String) {
        mAlarmService.setExamAlarm(convertMillis(date), rq, SID, buildingNo)
    }

    private fun convertMillis(data: String): Long {
        var sp = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        var date: Date = sp.parse(data)
        var millis: Long = date.time

        return millis
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

    private fun randomId(): Int {
        var randomId =
            "3${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}".toInt()
        val list = arrayListOf<Int>()
        var checked = false

        mSubjectViewModel.readAllData.observe(viewLifecycleOwner, { subject ->
            mSubject = subject

            for (i in 0..mSubject.size - 1) {

                list.add(mSubject[i].id)
            }

            while (checked == false) {
                if (randomId in list) {
                    randomId =
                        "3${(0..9).random()}${(0..9).random()}${(0..9).random()}${(0..9).random()}".toInt()
                    println(randomId)
                } else {
                    checked = true
                }
            }
        })
        return randomId
    }



}