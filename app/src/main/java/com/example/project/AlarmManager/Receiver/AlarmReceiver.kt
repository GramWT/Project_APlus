package com.example.project.AlarmManager.Receiver

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.text.format.DateFormat
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
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
        var SID = intent.getStringExtra("sid")


        var utcDate = Instant.ofEpochMilli(timeInMillis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        var start2 = LocalDateTime.now()

        var duration = Duration.between(start2,utcDate)
        duration = duration.minusDays(duration.toDays())
        var period = Period.between(start2.toLocalDate(),utcDate.toLocalDate())

        Log.d("Data","Receiver : ${period.years} years, ${period.months}" +
                " month, ${period.days} days, ${duration.toHours()} hours, " +
                "${duration.toMinutes()%60} minutes" )

        var text = "Remaining ${period.years} y, ${period.months} m, ${period.days} d, ${duration.toHours()} h, ${duration.toMinutes()%60} m"

        var text2 = "Remaining : ${period.months} M, ${period.days} D, ${duration.toHours()} H, ${duration.toMinutes()%60} m"

        val id_subject = RequestId?.substring(2,RequestId.length)
        val state = RequestId?.substring(0)






        when(action){
            "ACTION_SET_EXTRA" -> {
                AlarmNotification(context,id_subject,text2,RequestId.toString(), SID.toString())
                println(id_subject)
                println(state)

            }
        }
    }

    private fun AlarmNotification(context: Context,title: String?,message: String,rq:String,SID:String){


        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val channelId = "exam"
        val notification = NotificationCompat.Builder(context,channelId).apply {
            setSmallIcon(R.drawable.ic_baseline_library_books_24)
            setContentTitle(SID)
            setContentText(message)
            setSound(sound)
            color = ContextCompat.getColor(context,R.color.color_green_2EC1AC)
            setStyle(NotificationCompat.BigTextStyle().bigText(message))

            val intent1 = context.packageManager.getLaunchIntentForPackage(context.packageName)
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.akexorcist.dev"))
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
            setContentIntent(pendingIntent)

            val ss = "geo:0,0?q=13.82210271562129,100.51270047443425(Google+Bangkok)"
            val uri = Uri.parse(ss)
            val intent2 = Intent(Intent.ACTION_VIEW,uri)
            intent2.setPackage("com.google.android.apps.maps")

            val pendingIntent2 = PendingIntent.getActivity(context, 0, intent2, 0)
            addAction(R.mipmap.ic_launcher,"navigate",pendingIntent2)


            setAutoCancel(true)


        }.build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(rq.toInt(), notification)
    }


    private fun convertDate(timeInMillis: Long): String =
            DateFormat.format("dd/MM/yyyy hh:mm:ss",timeInMillis).toString()











}