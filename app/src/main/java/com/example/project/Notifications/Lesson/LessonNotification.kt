package com.example.project.Notifications.Lesson

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.DataBase.model.Lesson
import com.example.project.DataBase.viewmodel.LessonViewModel
import com.example.project.databinding.FragmentLessonNotificationBinding


class LessonNotification : Fragment() {

    private lateinit var binding: FragmentLessonNotificationBinding
    private lateinit var mLessonModel: LessonViewModel
    private var gridLayoutManager: GridLayoutManager? = null

    private lateinit var lessonList: List<Lesson>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mLessonModel = ViewModelProvider(this).get(LessonViewModel::class.java)
        mLessonModel.readAllData.observe(viewLifecycleOwner, { lesson ->
            lessonList = lesson


            if (lessonList.isEmpty()) {
                val action = LessonNotificationDirections.actionLessonNotification3ToDirectToLessonManagement()
                findNavController().navigate(action)
                println(lessonList.size)
            } else {
                println(lessonList.size)
            }
        })

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLessonNotificationBinding.inflate(layoutInflater)

        mLessonModel = ViewModelProvider(this).get(LessonViewModel::class.java)

        val at = activity as Context


        var adapter = LessonAdapter(mLessonModel, at)

        binding.lessonRecyclerView.adapter = adapter


        gridLayoutManager = GridLayoutManager(requireContext(), 1, LinearLayoutManager.VERTICAL, false)
        binding.lessonRecyclerView.layoutManager = gridLayoutManager

        mLessonModel.readAllData.observe(viewLifecycleOwner, { lesson ->
            adapter.setData(lesson)
        })

        return binding.root
    }

}