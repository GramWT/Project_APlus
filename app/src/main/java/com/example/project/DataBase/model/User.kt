package com.example.project.DataBase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
        @PrimaryKey(autoGenerate = true)
        val id:Int,
        val name:String,
        val avatar:Int
)