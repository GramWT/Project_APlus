package com.example.project.DataBase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "subject_upload_table")
data class SubjectUpload(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val sid:String,
    val Name:String,
    val mid_building:String,
    val mid_room:String,
    val mid_begin_time:String,
    val mid_end_time:String,
    val mid_date:String,
    val final_building:String,
    val final_room:String,
    val final_begin_time:String,
    val final_end_time:String,
    val final_date:String,
):Parcelable