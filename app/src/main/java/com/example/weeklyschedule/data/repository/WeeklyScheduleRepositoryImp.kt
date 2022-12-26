package com.example.weeklyschedule.data.repository

import androidx.annotation.WorkerThread
import com.example.weeklyschedule.data.local.entities.WeeklySchedule
import com.example.weeklyschedule.data.local.daos.WeeklyScheduleDao
import com.example.weeklyschedule.domain.WeeklyScheduleRepository
import kotlinx.coroutines.flow.Flow

class WeeklyScheduleRepositoryImp (private val weeklyDao: WeeklyScheduleDao):
    WeeklyScheduleRepository {

    val allDaysOfWeek: Flow<List<WeeklySchedule>> = weeklyDao.getAllWeeklySchedule()
    override fun getAllWeek(): Flow<List<WeeklySchedule>> {
       return weeklyDao.getAllWeeklySchedule()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(weeklySchedule: WeeklySchedule) {
        weeklyDao.insert(weeklySchedule)
    }
    fun getAllDays() {
        weeklyDao.getAllWeeklySchedule()
    }
    override suspend fun deleteADay(id: Int) {
//        weeklyDao.deleteADay(id)
    }
    override suspend fun update(weeklySchedule: WeeklySchedule) {
        weeklyDao.updateADay(weeklySchedule)
    }
     fun getADay(id :Int) {
        weeklyDao.observeADayById(id)
    }
}