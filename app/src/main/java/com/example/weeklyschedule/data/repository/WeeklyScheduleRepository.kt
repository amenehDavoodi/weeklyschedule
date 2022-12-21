package com.example.weeklyschedule.data.repository

import androidx.annotation.WorkerThread
import com.example.weeklyschedule.data.local.WeeklySchedule
import com.example.weeklyschedule.data.local.daos.WeeklyScheduleDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeeklyScheduleRepository (private val weeklyDao: WeeklyScheduleDao){

    val allDaysOfWeek: Flow<List<WeeklySchedule>> = weeklyDao.getAllWeeklySchedule()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(weeklySchedule: WeeklySchedule) {
        weeklyDao.insert(weeklySchedule)
    }
    fun getAllDays() {
        weeklyDao.getAllWeeklySchedule()
    }
    suspend fun deleteADay(id: Int) {
//        weeklyDao.deleteADay(id)
    }
    suspend fun update(weeklySchedule: WeeklySchedule) {
        weeklyDao.updateADay(weeklySchedule)
    }
}