package com.example.project.AlarmManager.Receiver

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.project.Constants.Constants
import com.example.project.R
import java.time.*


class AlarmReceiver : BroadcastReceiver() {
    @SuppressLint("NewApi")
    override fun onReceive(context: Context, intent: Intent) {

        val timeInMillis = intent.getLongExtra(Constants.EXTRA_EXACT_ALARM_TIME, 0L)
        val action = intent.getStringExtra("action")
        val requestId = intent.getStringExtra("rq")
        val examId = intent.getStringExtra("sid")
        val priority = intent.getStringExtra("priority")
        val buildingNo = intent.getStringExtra("buildingNo")
        val eventTitle = intent.getStringExtra("eventTitle")
        val timeRemaining = intent.getStringExtra("timeRemaining")
        val reminderTime = intent.getStringExtra("reminderTime")

        val utcDate =
            Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        val nowDateTime = LocalDateTime.now()

        var duration = Duration.between(nowDateTime, utcDate)
        duration = duration.minusDays(duration.toDays())
        val period = Period.between(nowDateTime.toLocalDate(), utcDate.toLocalDate())

        val countdownExam =
            "Remaining : ${period.months} M, ${period.days} D, ${duration.toHours()} H, ${duration.toMinutes() % 60} m"

        when (action) {
            Constants.SET_EXAM_NOTIFICATION -> {
                alarmExamNotification(
                    context,
                    countdownExam,
                    requestId.toString(),
                    examId.toString(),
                    buildingNo.toString()
                )
            }

            Constants.SET_EVENT_NOTIFICATION -> {
                alarmEventNotification(
                    context,
                    eventTitle.toString(),
                    requestId.toString(),
                    priority.toString(),
                    timeRemaining.toString()
                )
            }

            Constants.SET_REMINDER_NOTIFICATION ->{
                alarmReminderNotification(
                    context,
                    eventTitle.toString(),
                    requestId.toString(),
                    priority.toString(),
                    reminderTime.toString()
                )
            }
        }
    }


    private fun alarmExamNotification(
        context: Context,
        message: String,
        requestId: String,
        examId: String,
        buildingNo: String
    ) {
        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val channelId = "exam"
        val notification = NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.drawable.ic_baseline_library_books_24)
            setContentTitle(examId)
            setContentText(message)
            setSound(sound)
            color = ContextCompat.getColor(context, R.color.color_green)
            setStyle(NotificationCompat.BigTextStyle().bigText(message))
            val map = findLocation(buildingNo)
            val uri = Uri.parse(map)
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            mapIntent.setPackage("com.google.android.apps.maps")
            val pendingMapIntent = PendingIntent.getActivity(context, 0, mapIntent, 0)
            addAction(R.mipmap.ic_launcher, "navigate", pendingMapIntent)
            setAutoCancel(true)

        }.build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(requestId.toInt(), notification)
    }

    private fun alarmEventNotification(
        context: Context,
        message: String,
        requestId: String,
        priority: String,
        timeRemaining:String
    ) {
        val messageTitle = "Title : $message"
        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        var channelId = "exam"
        when(priority){
            "HIGH" -> {
                channelId = "event_high"
            }
            "NORMAL" -> {
                channelId = "event_default"
            }
            "LOW" -> {
                channelId = "event_low"
            }
        }
        val notification = NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.drawable.ic_baseline_library_books_24)
            setContentTitle(messageTitle)
            setContentText(timeRemaining)
            setSound(sound)
            color = ContextCompat.getColor(context, R.color.color_green)
            setStyle(NotificationCompat.BigTextStyle().bigText(timeRemaining))
            setAutoCancel(true)

        }.build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(requestId.toInt(), notification)
    }

    private fun alarmReminderNotification(
        context: Context,
        message: String,
        requestId: String,
        priority: String,
        duringReminder:String
    ) {
        val messageTitle = "Title : $message"
        val mainMessage = "Time : $duringReminder"
        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        var channelId = "exam"
        when(priority){
            "HIGH" -> {
                channelId = "event_high"
            }
            "NORMAL" -> {
                channelId = "event_default"
            }
            "LOW" -> {
                channelId = "event_low"
            }
        }
        val notification = NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.drawable.ic_baseline_library_books_24)
            setContentTitle(messageTitle)
            setContentText(mainMessage)
            setSound(sound)
            color = ContextCompat.getColor(context, R.color.color_green)
            setStyle(NotificationCompat.BigTextStyle().bigText(mainMessage))
            setAutoCancel(true)

        }.build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(requestId.toInt(), notification)
    }

    private fun findLocation(Building: String): String {

        val buildingNo: String

        when (Building) {

            "89" -> {
                buildingNo = "geo:0,0?q=13.82210271562129,100.51270047443425(Google+Bangkok)"
            }

            "81" -> {
                buildingNo = "geo:0,0?q=13.821240667570667,100.5136312052945(Google+Bangkok)"
            }

            "82" -> {
                buildingNo = "geo:0,0?q=13.82170596381349,100.513040349729(Google+Bangkok)"
            }

            "83" -> {
                buildingNo = "geo:0,0?q=13.822032743778463,100.5133276268752(Google+Bangkok)"
            }

            "84" -> {
                buildingNo = "geo:0,0?q=13.82173943885497,100.51380368615145(Google+Bangkok)"
            }

            "85" -> {
                buildingNo = "geo:0,0?q=13.821382371493481,100.51379876140038(Google+Bangkok)"
            }

            "86" -> {
                buildingNo = "geo:0,0?q=13.822451248310156,100.51326502359224(Google+Bangkok)"
            }

            "88" -> {
                buildingNo = "geo:0,0?q=13.822563091586796,100.5130854227556(Google+Bangkok)"
            }

            else -> {
                buildingNo = "geo:0,0?q=13.82210271562129,100.51270047443425(Google+Bangkok)"
            }
        }

        return buildingNo
    }

}