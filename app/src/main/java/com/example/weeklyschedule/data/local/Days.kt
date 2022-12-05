package com.example.weeklyschedule.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Days(
    val dayName: String,
    @PrimaryKey
    val dayId: Int
)
