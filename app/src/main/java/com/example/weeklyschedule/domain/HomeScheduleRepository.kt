package com.example.weeklyschedule.domain

import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.data.local.entities.WeeklySchedule
import kotlinx.coroutines.flow.Flow

interface HomeScheduleRepository {
    fun getAllWeek(): Flow<List<WeeklySchedule>>
    fun getTodayCourses():Flow<WeeklySchedule>
    fun getAllCourse(): Flow<List<Courses>>
}