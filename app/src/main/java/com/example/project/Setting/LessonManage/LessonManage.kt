package com.example.project.Setting.LessonManage

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.DataBase.viewmodel.LessonViewModel
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.MainActivity
import com.example.project.R
import com.example.project.Setting.SubjectsManage.SubjectsManageAdapter
import com.example.project.Setting.SubjectsManage.SubjectsManageDirections
import com.example.project.databinding.FragmentLessonManageBinding
import kotlinx.android.synthetic.main.activity_main.*


class LessonManage : Fragment() {

    private lateinit var binding:FragmentLessonManageBinding
    private var gridLayoutManager: GridLayoutManager? = null
    private lateinit var mLessonModel: LessonViewModel

    override fun onResume() {
        super.onResume()
        val a = activity as MainActivity
        a.bottom_navigation.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLessonManageBinding.inflate(layoutInflater)
        var adapter = LessonManageAdapter()
        
        mLessonModel = ViewModelProvider(this).get(LessonViewModel::class.java)
        mLessonModel.readAllData.observe(viewLifecycleOwner,{lesson ->
            adapter.setData(lesson)
        })

        binding.lessonManageRecycleView.adapter = adapter
        gridLayoutManager = GridLayoutManager(requireContext(),1, LinearLayoutManager.VERTICAL,false)

        binding.lessonManageRecycleView.layoutManager = gridLayoutManager

        binding.backButtonLsm.setOnClickListener {

            val a = activity as MainActivity
            a.bottom_navigation.visibility = View.VISIBLE

            if (a.bottom_navigation.selectedItemId == R.id.menu_lesson){
                a.bottom_navigation.selectedItemId = R.id.menu_setting
            }

            else if (a.bottom_navigation.selectedItemId == R.id.menu_setting){
                val action = LessonManageDirections.actionLessonManageToNavSetting2()
                findNavController().navigate(action)
            }
        }

        binding.deleteAllButtonLsm.setOnClickListener {
            deleteAllSubjects()
        }

        binding.addLessonButtonLsm.setOnClickListener {
            val action = LessonManageDirections.actionLessonManageToLessonManageAdd()
            findNavController().navigate(action)
        }


        return binding.root
    }

    private fun deleteAllSubjects(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){ _, _ ->
            mLessonModel.deleteAllLesson()
            Toast.makeText(
                    requireContext(),
                    "Successfully Removed All Subjects",
                    Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No"){ _, _ ->}
        builder.setTitle("Delete All Subjects ?")
        builder.setMessage("Are you sure you want to delete all subject?")
        builder.create().show()

    }

}