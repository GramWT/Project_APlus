package com.example.project.DataBase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "event_table")
data class Event (
        @PrimaryKey(autoGenerate = true)
        val id:Int,
        val title:String,
        val date_begin:String,
        val date_end:String,
        val time_begin:String,
        val time_end:String,
        val state:String,
        val description:String,
        val time_notification_1:String,
        val time_notification_2:String,
        val time_notification_3:String,
        val time_notification_4:String,
        val time_notification_5:String,
        val location:String
        ):Parcelable