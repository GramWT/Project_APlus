package com.example.project.AlarmManager.Receiver

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.format.DateFormat
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.project.Constants.Constants
import com.example.project.R
import java.text.Format
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit
import java.time.temporal.ChronoUnit
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds


class AlarmReceiver:BroadcastReceiver() {
    @SuppressLint("NewApi")
    override fun onReceive(context: Context, intent: Intent) {

        val timeInMillis = intent.getLongExtra(Constants.EXTRA_EXACT_ALARM_TIME,0L)
        val action = intent.getStringExtra("action")
        val RequestId = intent.getStringExtra("rq")


        var utcDate = Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        var start2 = LocalDateTime.now()

        var duration = Duration.between(start2,utcDate)
        duration = duration.minusDays(duration.toDays())
        var period = Period.between(start2.toLocalDate(),utcDate.toLocalDate())

        Log.d("Data","Receiver : ${period.years} years, ${period.months}" +
                " month, ${period.days} days, ${duration.toHours()} hours, " +
                "${duration.toMinutes()%60} minutes" )

        var text = "Remaining time ${period.years} y, ${period.months} m, ${period.days} d, ${duration.toHours()} h, ${duration.toMinutes()%60} m"



        when(action){
            "ACTION_SET_EXTRA" -> {
                AlarmNotification(context,RequestId,text,RequestId.toString())

            }
        }
    }

    private fun AlarmNotification(context: Context,title: String?,message: String,rq:String){


        val channelId = "exam"
        val notification = NotificationCompat.Builder(context,channelId).apply {
            setSmallIcon(R.drawable.ic_launcher_foreground)
            setContentTitle(title)
            setContentText(message)
        }.build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(rq.toInt(), notification)
    }


    private fun convertDate(timeInMillis: Long): String =
            DateFormat.format("dd/MM/yyyy hh:mm:ss",timeInMillis).toString()











}