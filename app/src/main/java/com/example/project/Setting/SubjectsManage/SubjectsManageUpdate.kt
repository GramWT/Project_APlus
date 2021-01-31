package com.example.project.Setting.SubjectsManage

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.project.AlarmManager.Service.AlarmService
import com.example.project.DataBase.model.Subject
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_subjects_manage_update.*
import kotlinx.android.synthetic.main.fragment_subjects_manage_update.view.*
import java.text.SimpleDateFormat
import java.util.*


class SubjectsManageUpdate : Fragment() {

    private val args by navArgs<SubjectsManageUpdateArgs>()
    private lateinit var mSubjectViewModel: SubjectViewModel
    private lateinit var mAlarmService: AlarmService


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_subjects_manage_update, container, false)
        mSubjectViewModel = ViewModelProvider(this).get(SubjectViewModel::class.java)

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
            val action = SubjectsManageUpdateDirections.actionSubjectsManageUpdateToSubjectsManageNav()
            view.findNavController().navigate(action)
        }

        view.admit_button_update.setOnClickListener {
            val action = SubjectsManageUpdateDirections.actionSubjectsManageUpdateToSubjectsManageNav()
            updateItem()
            Toast.makeText(requireContext(),"Update Successfully", Toast.LENGTH_SHORT).show()
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

        view.deleteButton.setOnClickListener {
            deleteSubject()
        }

        return view
    }

    private fun updateItem(){
        val subject = Subject(args.subject.id,code_manager_update.text.toString(),name_manager_update.text.toString(),building_mid_manager_update.text.toString(),
                room_mid_manager_update.text.toString(),time_mid_begin_manager_update.text.toString(), time_mid_end_manager_update.text.toString(),date_mid_manager_update.text.toString()
                ,building_final_manager_update.text.toString(),room_final_manager_update.text.toString(),time_final_begin_manager_update.text.toString(),time_final_end_manager_update.text.toString(),
                date_final_manager_update.text.toString())
        mSubjectViewModel.updateSubject(subject)
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

    private fun deleteSubject(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){ _, _ ->
            mSubjectViewModel.deleteSubject(args.subject)
            Toast.makeText(requireContext(),"Successfully deleted ",Toast.LENGTH_SHORT).show()
            val action = SubjectsManageUpdateDirections.actionSubjectsManageUpdateToSubjectsManageNav()
            findNavController().navigate(action)

        }
        builder.setNegativeButton("No"){ _ ,_ ->}
        builder.setTitle("Delete ?")
        builder.setMessage("Are you sure you want to delete?")
        builder.show()
    }


}