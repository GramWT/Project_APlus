package com.example.project.Notifications.Event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.DataBase.viewmodel.EventViewModel
import com.example.project.DataBase.viewmodel.SubjectViewModel
import com.example.project.R
import com.example.project.databinding.FragmentEventNotificationBinding


class EventNotification : Fragment() {

    private lateinit var binding:FragmentEventNotificationBinding
    private lateinit var mEventModel: EventViewModel
    private var gridLayoutManager: GridLayoutManager? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventNotificationBinding.inflate(layoutInflater)


        var adapter = EventAdapter()

        binding.eventRecyclerView.adapter = adapter


        gridLayoutManager = GridLayoutManager(requireContext(),1,LinearLayoutManager.VERTICAL,false)

        binding.eventRecyclerView.layoutManager = gridLayoutManager

        mEventModel = ViewModelProvider(this).get(EventViewModel::class.java)
        mEventModel.readAllData.observe(viewLifecycleOwner,{event ->
            adapter.setData(event)
        })





        binding.addEventButton.setOnClickListener {
            val action = EventNotificationDirections.actionEventNotificationToEventNotificationAdd()
            findNavController().navigate(action)
        }

        return binding.root

    }



}