package com.example.project

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class InitialApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        notificationChannelInit(applicationContext)
    }

    private fun notificationChannelInit(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val groupId = "kmutnb"
            val groupName = "KMUTNB"
            val group = NotificationChannelGroup(groupId, groupName)

            val examChannelId = "exam"
            val channelName1 = "Exam"
            val channel1 = NotificationChannel(examChannelId, channelName1, NotificationManager.IMPORTANCE_HIGH)
            channel1.group = groupId

            val highChannelId = "event_high"
            val channelName2 = "Event High"
            val channel2 = NotificationChannel(highChannelId, channelName2, NotificationManager.IMPORTANCE_HIGH)
            channel2.group = groupId

            val normalChannelId = "event_default"
            val channelName3 = "Event Default"
            val channel3 = NotificationChannel(normalChannelId, channelName3, NotificationManager.IMPORTANCE_DEFAULT)
            channel3.group = groupId

            val lowChannelId = "event_low"
            val channelName4 = "Event Low"
            val channel4 = NotificationChannel(lowChannelId, channelName4, NotificationManager.IMPORTANCE_LOW)
            channel4.group = groupId


            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannelGroup(group)
            manager.createNotificationChannel(channel1)
            manager.createNotificationChannel(channel2)
            manager.createNotificationChannel(channel3)
            manager.createNotificationChannel(channel4)
        }
    }
}