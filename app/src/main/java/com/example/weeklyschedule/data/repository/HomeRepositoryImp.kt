package com.example.weeklyschedule.data.repository

import com.example.weeklyschedule.data.local.daos.WeeklyScheduleDao
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.data.local.entities.WeeklySchedule
import com.example.weeklyschedule.domain.HomeScheduleRepository
import com.example.weeklyschedule.domain.ListCourses
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImp (private val homeDao: WeeklyScheduleDao):
    HomeScheduleRepository {
    override fun getAllWeek(): Flow<List<WeeklySchedule>> {
       return homeDao.getAllWeeklySchedule()
    }

    override fun getTodayCourses(): Flow<WeeklySchedule> {
        TODO("Not yet implemented")
    }

    override fun getTodayCourses(dayId:Int): Flow<List<Courses>> = homeDao.observeCoursesOfADayById(dayId)

    override fun getAllCourse(): Flow<ListCourses> = homeDao.observeAllCourse()

}