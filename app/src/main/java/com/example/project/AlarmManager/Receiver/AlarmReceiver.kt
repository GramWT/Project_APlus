package com.example.project.AlarmManager.Receiver

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
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

        val utcDate =
            Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        val nowDateTime = LocalDateTime.now()

        var duration = Duration.between(nowDateTime, utcDate)
        duration = duration.minusDays(duration.toDays())
        val period = Period.between(nowDateTime.toLocalDate(), utcDate.toLocalDate())

        Log.d(
            "Data", "Receiver : ${period.years} years, ${period.months}" +
                    " month, ${period.days} days, ${duration.toHours()} hours, " +
                    "${duration.toMinutes() % 60} minutes"
        )

        val countdownExam =
            "Remaining : ${period.months} M, ${period.days} D, ${duration.toHours()} H, ${duration.toMinutes() % 60} m"

        when (action) {
            Constants.SET_EXAM_NOTIFICATION -> {
                alarmExamNotification(
                    context,
                    countdownExam,
                    requestId.toString(),
                    examId.toString()
                )
            }
        }
    }

    private fun alarmExamNotification(
        context: Context,
        message: String,
        requestId: String,
        examId: String
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
            val intent1 = context.packageManager.getLaunchIntentForPackage(context.packageName)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0)
            setContentIntent(pendingIntent)
            val map = "geo:0,0?q=13.82210271562129,100.51270047443425(Google+Bangkok)"
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

}