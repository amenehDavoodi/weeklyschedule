package com.example.weeklyschedule.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeeklySchedule(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val dayId:Int,
    val courseId:Int,
    val breakId:Int
)
