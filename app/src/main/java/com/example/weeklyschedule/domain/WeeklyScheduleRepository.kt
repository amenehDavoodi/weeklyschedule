package com.example.weeklyschedule.domain

import com.example.weeklyschedule.data.local.entities.WeeklySchedule
import kotlinx.coroutines.flow.Flow

interface WeeklyScheduleRepository {
    fun getAllWeek():Flow<List<WeeklySchedule>>
    suspend fun insert(weeklySchedule: WeeklySchedule)
    suspend fun deleteADay(id: Int)
    suspend fun update(weeklySchedule: WeeklySchedule)
}