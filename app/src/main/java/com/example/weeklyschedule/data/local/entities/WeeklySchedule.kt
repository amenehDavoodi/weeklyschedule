package com.example.weeklyschedule.data.local.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class WeeklySchedule(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val dayId:Int,
    val courseId:Int,
    @NonNull
    val breakId:Int=0
)

