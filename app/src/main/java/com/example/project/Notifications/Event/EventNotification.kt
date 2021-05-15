package com.example.project.Notifications.Event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.DataBase.viewmodel.EventViewModel
import com.example.project.R
import com.example.project.databinding.FragmentEventNotificationBinding
import java.util.*
import kotlin.concurrent.schedule


class EventNotification : Fragment() {

    private lateinit var binding: FragmentEventNotificationBinding
    private lateinit var mEventModel: EventViewModel
    private var gridLayoutManager: GridLayoutManager? = null

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_open_anim) }
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.rotate_close_anim) }
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.from_buttom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.to_buttom_anim) }
    private val shakeItem: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.shake) }
    private val shakeItem2: Animation by lazy { AnimationUtils.loadAnimation(context, R.anim.shake_v2) }
    private var clicked = false

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventNotificationBinding.inflate(layoutInflater)

        binding.glassLayout.bringToFront()
        binding.glassLayout.visibility = View.INVISIBLE


        var adapter = EventAdapter()

        binding.eventRecyclerView.adapter = adapter



        gridLayoutManager = GridLayoutManager(requireContext(), 1, LinearLayoutManager.VERTICAL, false)

        binding.eventRecyclerView.layoutManager = gridLayoutManager

        mEventModel = ViewModelProvider(this).get(EventViewModel::class.java)
        mEventModel.readAllData.observe(viewLifecycleOwner, { event ->
            adapter.setData(event)
        })

        binding.addEventButton.setOnClickListener {
            onAddButtonClicked()
        }

        binding.glassLayout.setOnClickListener {
            onAddButtonClicked()
        }

        binding.eventButton.setOnClickListener {
            val action = EventNotificationDirections.actionEventNotificationToEventNotificationAdd()
            findNavController().navigate(action)
        }

        binding.reminderButton.setOnClickListener {
            val action = EventNotificationDirections.actionEventNotificationToReminderNotificationAdd()
            findNavController().navigate(action)
        }


        return binding.root

    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        onBlur(clicked)

        clicked = !clicked
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            binding.eventButton.visibility = View.VISIBLE
            binding.reminderButton.visibility = View.VISIBLE
            binding.eventText.visibility = View.VISIBLE
            binding.reminderText.visibility = View.VISIBLE
        } else {
            binding.eventButton.visibility = View.INVISIBLE
            binding.reminderButton.visibility = View.INVISIBLE
            binding.eventText.visibility = View.INVISIBLE
            binding.reminderText.visibility = View.INVISIBLE
        }
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked) {
            binding.eventButton.startAnimation(fromBottom)
            binding.reminderButton.startAnimation(fromBottom)
            binding.addEventButton.startAnimation(rotateOpen)
            binding.eventText.startAnimation(fromBottom)
            binding.reminderText.startAnimation(fromBottom)
            Timer().schedule(200) {
                binding.eventButton.startAnimation(shakeItem)
                binding.reminderButton.startAnimation(shakeItem2)
                binding.eventText.startAnimation(shakeItem)
                binding.reminderText.startAnimation(shakeItem2)
            }

        } else {
            binding.eventButton.startAnimation(toBottom)
            binding.reminderButton.startAnimation(toBottom)
            binding.addEventButton.startAnimation(rotateClose)
            binding.eventText.startAnimation(toBottom)
            binding.reminderText.startAnimation(toBottom)
        }
    }

    private fun onBlur(clicked: Boolean) {
        if (!clicked) {
            binding.glassLayout.visibility = View.VISIBLE
        } else {
            binding.glassLayout.visibility = View.INVISIBLE
        }
    }


}