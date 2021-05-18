package com.example.project.AlarmManager.Service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.project.AlarmManager.Receiver.AlarmReceiver
import com.example.project.Constants.Constants
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.Period
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit

class AlarmService(private val context: Context) {

    private val alarmManager: AlarmManager? =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?


    @RequiresApi(Build.VERSION_CODES.O)
    fun setReminderAlarm(requestCode: Int, title: String, priority: String, duringReminder:String, nowTime:String) {
        val requestCode = "1$requestCode"
        val intent1 = Intent(context, AlarmReceiver::class.java)

        intent1.apply {
            putExtra("rq", requestCode)
            putExtra("action", Constants.SET_REMINDER_NOTIFICATION)
            putExtra("eventTitle", title)
            putExtra("priority", priority)
            putExtra("reminderTime", duringReminder)
        }

        val pendingIntent1 = PendingIntent.getBroadcast(
            context,
            requestCode.toInt(),
            intent1,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (convertMillis(nowTime) > System.currentTimeMillis()) {
            alarmManager?.setExact(AlarmManager.RTC_WAKEUP, convertMillis(nowTime), pendingIntent1)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun setEventAlarm(requestCode: Int, title: String, priority: String,destinationTime:String,nowTime:String) {
        val requestCode = "1$requestCode"
        val intent1 = Intent(context, AlarmReceiver::class.java)

        intent1.apply {
            putExtra("rq", requestCode)
            putExtra("action", Constants.SET_EVENT_NOTIFICATION)
            putExtra("eventTitle", title)
            putExtra("priority", priority)
            putExtra("timeRemaining", showRemainingTime(destinationTime,nowTime))
        }

        val pendingIntent1 = PendingIntent.getBroadcast(
            context,
            requestCode.toInt(),
            intent1,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (convertMillis(nowTime) > System.currentTimeMillis()) {
            alarmManager?.setExact(AlarmManager.RTC_WAKEUP, convertMillis(nowTime), pendingIntent1)
        }
    }

    fun cancelEventAlarm(RequestCode: Int) {

        val rq1 = '1' + RequestCode.toString()
        val intent1 = Intent(context, AlarmReceiver::class.java)

        val pendingIntent1 = PendingIntent.getBroadcast(
            context,
            rq1.toInt(),
            intent1,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager?.cancel(pendingIntent1)
    }

    fun setExamAlarm(TimeInMillis: Long, RequestCode: Int, examId: String, buildingNo: String) {

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
            putExtra("action", Constants.SET_EXAM_NOTIFICATION)
            putExtra("sid", examId)
            putExtra("buildingNo", buildingNo)
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, TimeInMillis)
        }

        intent2.apply {
            putExtra("rq", rq2)
            putExtra("action", Constants.SET_EXAM_NOTIFICATION)
            putExtra("sid", examId)
            putExtra("buildingNo", buildingNo)
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, TimeInMillis)
        }

        intent3.apply {
            putExtra("rq", rq3)
            putExtra("action", Constants.SET_EXAM_NOTIFICATION)
            putExtra("sid", examId)
            putExtra("buildingNo", buildingNo)
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, TimeInMillis)
        }

        intent4.apply {
            putExtra("rq", rq4)
            putExtra("action", Constants.SET_EXAM_NOTIFICATION)
            putExtra("sid", examId)
            putExtra("buildingNo", buildingNo)
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, TimeInMillis)
        }

        intent5.apply {
            putExtra("rq", rq5)
            putExtra("action", Constants.SET_EXAM_NOTIFICATION)
            putExtra("sid", examId)
            putExtra("buildingNo", buildingNo)
            putExtra(Constants.EXTRA_EXACT_ALARM_TIME, TimeInMillis)
        }


        val pendingIntent1 = PendingIntent.getBroadcast(
            context,
            rq1.toInt(),
            intent1,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val pendingIntent2 = PendingIntent.getBroadcast(
            context,
            rq2.toInt(),
            intent2,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val pendingIntent3 = PendingIntent.getBroadcast(
            context,
            rq3.toInt(),
            intent3,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val pendingIntent4 = PendingIntent.getBroadcast(
            context,
            rq4.toInt(),
            intent4,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val pendingIntent5 = PendingIntent.getBroadcast(
            context,
            rq5.toInt(),
            intent5,
            PendingIntent.FLAG_UPDATE_CURRENT
        )


        if ((TimeInMillis - TimeUnit.DAYS.toMillis(30)) > System.currentTimeMillis()) {
            alarmManager?.setExact(
                AlarmManager.RTC_WAKEUP,
                TimeInMillis - TimeUnit.DAYS.toMillis(30),
                pendingIntent1
            )
        }

        if ((TimeInMillis - TimeUnit.DAYS.toMillis(14)) > System.currentTimeMillis()) {
            alarmManager?.setExact(
                AlarmManager.RTC_WAKEUP,
                TimeInMillis - TimeUnit.DAYS.toMillis(14),
                pendingIntent2
            )
        }

        if ((TimeInMillis - TimeUnit.DAYS.toMillis(7)) > System.currentTimeMillis()) {
            alarmManager?.setExact(
                AlarmManager.RTC_WAKEUP,
                TimeInMillis - TimeUnit.DAYS.toMillis(7),
                pendingIntent3
            )
        }

        if ((TimeInMillis - TimeUnit.DAYS.toMillis(1)) > System.currentTimeMillis()) {
            alarmManager?.setExact(
                AlarmManager.RTC_WAKEUP,
                TimeInMillis - TimeUnit.DAYS.toMillis(1),
                pendingIntent4
            )
        }

        if ((TimeInMillis - TimeUnit.HOURS.toMillis(1)) > System.currentTimeMillis()) {
            alarmManager?.setExact(
                AlarmManager.RTC_WAKEUP,
                TimeInMillis - TimeUnit.HOURS.toMillis(1),
                pendingIntent5
            )
        }

    }

    fun cancelExamAlarm(RequestCode: Int) {
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


        val pendingIntent1 = PendingIntent.getBroadcast(
            context,
            rq1.toInt(),
            intent1,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val pendingIntent2 = PendingIntent.getBroadcast(
            context,
            rq2.toInt(),
            intent2,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val pendingIntent3 = PendingIntent.getBroadcast(
            context,
            rq3.toInt(),
            intent3,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val pendingIntent4 = PendingIntent.getBroadcast(
            context,
            rq4.toInt(),
            intent4,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val pendingIntent5 = PendingIntent.getBroadcast(
            context,
            rq5.toInt(),
            intent5,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        alarmManager?.cancel(pendingIntent1)
        alarmManager?.cancel(pendingIntent2)
        alarmManager?.cancel(pendingIntent3)
        alarmManager?.cancel(pendingIntent4)
        alarmManager?.cancel(pendingIntent5)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showRemainingTime(destinationTime:String, nowTime: String):String{
        val utcDate = Instant.ofEpochMilli(convertMillis(destinationTime)).atZone(ZoneId.systemDefault()).toLocalDateTime()
        val notificationDateTime = Instant.ofEpochMilli(convertMillis(nowTime)).atZone(ZoneId.systemDefault()).toLocalDateTime()
        var duration = Duration.between(notificationDateTime, utcDate)
        duration = duration.minusDays(duration.toDays())
        val period = Period.between(notificationDateTime.toLocalDate(), utcDate.toLocalDate())

        val countdownExam =
            "Remaining : ${period.months} M, ${period.days} D, ${duration.toHours()} H, ${duration.toMinutes() % 60} m"

        return countdownExam
    }

    private fun convertMillis(data: String): Long {
        var sp = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        var date: Date = sp.parse(data)
        var millis: Long = date.time

        return millis
    }

}