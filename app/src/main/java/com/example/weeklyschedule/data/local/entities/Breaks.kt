package com.example.weeklyschedule.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Breaks(
    @PrimaryKey
    val id: Int,
    val name: String
)
