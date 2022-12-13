package com.example.weeklyschedule.data.repository

import androidx.annotation.WorkerThread
import com.example.weeklyschedule.data.local.WeeklySchedule
import com.example.weeklyschedule.data.local.daos.WeeklyScheduleDao
import kotlinx.coroutines.flow.Flow

class WeeklyScheduleRepository (private val weeklyDao: WeeklyScheduleDao){
    val allWeek: Flow<List<WeeklySchedule>> = weeklyDao.getAllWeeklySchedule()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(weeklySchedule: WeeklySchedule) {
        weeklyDao.insert(weeklySchedule)
    }
}