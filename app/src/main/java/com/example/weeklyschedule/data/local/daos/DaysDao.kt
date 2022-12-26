package com.example.weeklyschedule.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.weeklyschedule.data.local.entities.Days

@Dao
interface DaysDao {
    @Query("SELECT * FROM Days")
    fun getAllDays(): List<Days>
    @Query("SELECT * FROM Days WHERE dayId = :id")
    fun observeDayById(id: Int): String
}