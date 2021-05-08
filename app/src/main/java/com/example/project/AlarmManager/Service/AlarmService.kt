package com.example.project.AlarmManager.Service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.project.AlarmManager.Receiver.AlarmReceiver
import com.example.project.Constants.Constants
import java.util.concurrent.TimeUnit

class AlarmService(private val context: Context) {

    private val alarmManager: AlarmManager? =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?

    fun setOnceAlarm(TimeInMillis: Long, RequestCode: Int, SID: String) {

        val rq1 = '1' + RequestCode.toString()
        val intent1 = Intent(context, AlarmReceiver::class.java)

        intent1.apply {
            putExtra("rq", rq1)
            putExtra("action", Constants.ACTION_SET_EXACT_ALARM)
            putExtra("sid", SID)
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, TimeInMillis)
        }

        var pendingIntent1 = PendingIntent.getBroadcast(context, rq1.toInt(), intent1, PendingIntent.FLAG_UPDATE_CURRENT)

        if (TimeInMillis > System.currentTimeMillis()) {
            alarmManager?.setExact(AlarmManager.RTC_WAKEUP, TimeInMillis, pendingIntent1)
        } else {
            println(TimeInMillis)
        }

    }

    fun cancelOnceAlarm(RequestCode: Int) {

        val rq1 = '1' + RequestCode.toString()
        val intent1 = Intent(context, AlarmReceiver::class.java)

        var pendingIntent1 = PendingIntent.getBroadcast(context, rq1.toInt(), intent1, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager?.cancel(pendingIntent1)
    }

    fun setExactAlarm(TimeInMillis: Long, RequestCode: Int, SID: String) {

        val rq1 = '1' + RequestCode.toString()
        val rq2 = '2' + RequestCode.toString()
        val rq3 = '3' + RequestCode.toString()
        val rq4 = '4' + RequestCode.toString()
        val rq5 = '5' + RequestCode.toString()

        val intent1 = Intent(context, AlarmReceiver::class.java)
        val intent2 = Intent(context, AlarmReceiver::class.java)
        val intent3 = Intent(context, AlarmReceiver::class.java)
        val intent4 = Intent(context, AlarmReceiver::class.java)
        val intent5 = Intent(context, AlarmReceiver::class.java)

        intent1.apply {
            putExtra("rq", rq1)
            putExtra("action", Constants.ACTION_SET_EXACT_ALARM)
            putExtra("sid", SID)
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, TimeInMillis)
        }

        intent2.apply {
            putExtra("rq", rq2)
            putExtra("action", Constants.ACTION_SET_EXACT_ALARM)
            putExtra("sid", SID)
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, TimeInMillis)
        }

        intent3.apply {
            putExtra("rq", rq3)
            putExtra("action", Constants.ACTION_SET_EXACT_ALARM)
            putExtra("sid", SID)
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, TimeInMillis)
        }

        intent4.apply {
            putExtra("rq", rq4)
            putExtra("action", Constants.ACTION_SET_EXACT_ALARM)
            putExtra("sid", SID)
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, TimeInMillis)
        }

        intent5.apply {
            putExtra("rq", rq5)
            putExtra("action", Constants.ACTION_SET_EXACT_ALARM)
            putExtra("sid", SID)
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, TimeInMillis)
        }


        var pendingIntent1 = PendingIntent.getBroadcast(context, rq1.toInt(), intent1, PendingIntent.FLAG_UPDATE_CURRENT)
        var pendingIntent2 = PendingIntent.getBroadcast(context, rq2.toInt(), intent2, PendingIntent.FLAG_UPDATE_CURRENT)
        var pendingIntent3 = PendingIntent.getBroadcast(context, rq3.toInt(), intent3, PendingIntent.FLAG_UPDATE_CURRENT)
        var pendingIntent4 = PendingIntent.getBroadcast(context, rq4.toInt(), intent4, PendingIntent.FLAG_UPDATE_CURRENT)
        var pendingIntent5 = PendingIntent.getBroadcast(context, rq5.toInt(), intent5, PendingIntent.FLAG_UPDATE_CURRENT)


        if ((TimeInMillis - TimeUnit.MINUTES.toMillis(25)) > System.currentTimeMillis()) {
            alarmManager?.setExact(AlarmManager.RTC_WAKEUP, TimeInMillis - TimeUnit.MINUTES.toMillis(25), pendingIntent1)
        }

        if ((TimeInMillis - TimeUnit.MINUTES.toMillis(20)) > System.currentTimeMillis()) {
            alarmManager?.setExact(AlarmManager.RTC_WAKEUP, TimeInMillis - TimeUnit.MINUTES.toMillis(20), pendingIntent2)
        }

        if ((TimeInMillis - TimeUnit.MINUTES.toMillis(15)) > System.currentTimeMillis()) {
            alarmManager?.setExact(AlarmManager.RTC_WAKEUP, TimeInMillis - TimeUnit.MINUTES.toMillis(15), pendingIntent3)
        }

        if ((TimeInMillis - TimeUnit.MINUTES.toMillis(10)) > System.currentTimeMillis()) {
            alarmManager?.setExact(AlarmManager.RTC_WAKEUP, TimeInMillis - TimeUnit.MINUTES.toMillis(10), pendingIntent4)
        }

        if ((TimeInMillis - TimeUnit.MINUTES.toMillis(5)) > System.currentTimeMillis()) {
            alarmManager?.setExact(AlarmManager.RTC_WAKEUP, TimeInMillis - TimeUnit.MINUTES.toMillis(5), pendingIntent5)
        }

    }


    fun cancelAlarm(RequestCode: Int) {
        val rq1 = '1' + RequestCode.toString()
        val rq2 = '2' + RequestCode.toString()
        val rq3 = '3' + RequestCode.toString()
        val rq4 = '4' + RequestCode.toString()
        val rq5 = '5' + RequestCode.toString()

        val intent1 = Intent(context, AlarmReceiver::class.java)
        val intent2 = Intent(context, AlarmReceiver::class.java)
        val intent3 = Intent(context, AlarmReceiver::class.java)
        val intent4 = Intent(context, AlarmReceiver::class.java)
        val intent5 = Intent(context, AlarmReceiver::class.java)


        var pendingIntent1 = PendingIntent.getBroadcast(context, rq1.toInt(), intent1, PendingIntent.FLAG_UPDATE_CURRENT)
        var pendingIntent2 = PendingIntent.getBroadcast(context, rq2.toInt(), intent2, PendingIntent.FLAG_UPDATE_CURRENT)
        var pendingIntent3 = PendingIntent.getBroadcast(context, rq3.toInt(), intent3, PendingIntent.FLAG_UPDATE_CURRENT)
        var pendingIntent4 = PendingIntent.getBroadcast(context, rq4.toInt(), intent4, PendingIntent.FLAG_UPDATE_CURRENT)
        var pendingIntent5 = PendingIntent.getBroadcast(context, rq5.toInt(), intent5, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager?.cancel(pendingIntent1)
        alarmManager?.cancel(pendingIntent2)
        alarmManager?.cancel(pendingIntent3)
        alarmManager?.cancel(pendingIntent4)
        alarmManager?.cancel(pendingIntent5)
    }

}