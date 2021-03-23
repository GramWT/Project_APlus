package com.example.project.DataBase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "lesson_table")
data class Lesson(
        @PrimaryKey(autoGenerate = true)
        val id:Int,
        val name:String,
        val l01:String,
        val l02:String,
        val l03:String,
        val l04:String,
        val l05:String,
        val l06:String,
        val l07:String,
        val l08:String,
        val l09:String,
        val l10:String,
        val l01_C:Int,
        val l02_C:Int,
        val l03_C:Int,
        val l04_C:Int,
        val l05_C:Int,
        val l06_C:Int,
        val l07_C:Int,
        val l08_C:Int,
        val l09_C:Int,
        val l10_C:Int
        ):Parcelable