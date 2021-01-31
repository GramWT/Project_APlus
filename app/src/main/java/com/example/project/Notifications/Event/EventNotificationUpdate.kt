package com.example.project.Notifications.Event

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project.MainActivity
import com.example.project.R
import kotlinx.android.synthetic.main.fragment_event_notification_update.view.*

class EventNotificationUpdate : Fragment() {


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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_event_notification_update, container, false)

        view.textView20.setText("111")
        return view
    }

}