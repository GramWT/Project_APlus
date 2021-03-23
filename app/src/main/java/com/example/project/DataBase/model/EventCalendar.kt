package com.example.project.DataBase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "event_calendar_table")
data class EventCalendar(
        @PrimaryKey(autoGenerate = true)
        val id:Int,
        val type:Int,
        val day:Int,
        val month:Int,
        val year:Int,
        val subject:String
):Parcelable