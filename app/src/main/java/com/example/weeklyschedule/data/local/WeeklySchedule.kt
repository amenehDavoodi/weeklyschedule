package com.example.weeklyschedule.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeeklySchedule(
    @PrimaryKey
    val id:Int,
    val dayId:Int,
    val courseId:Int
)
