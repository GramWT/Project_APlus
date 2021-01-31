package com.example.project

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class InitialApplication :Application(){

    override fun onCreate() {
        super.onCreate()
        initNotificationChannel(applicationContext)
    }

    fun initNotificationChannel(context: Context) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val groupId = "kmutnb"
            val groupName = "KMUTNB"
            val group = NotificationChannelGroup(groupId, groupName)

            val channelId1 = "exam"
            val channelName1 = "Exam"
            val channel1 = NotificationChannel(channelId1, channelName1, NotificationManager.IMPORTANCE_HIGH)
            channel1.group = groupId

            val channelId2 = "event"
            val channelName2 = "Event"
            val channel2 = NotificationChannel(channelId2, channelName2, NotificationManager.IMPORTANCE_HIGH)
            channel2.group = groupId



            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannelGroup(group)
            manager.createNotificationChannel(channel1)
            manager.createNotificationChannel(channel2)
        }
    }
}