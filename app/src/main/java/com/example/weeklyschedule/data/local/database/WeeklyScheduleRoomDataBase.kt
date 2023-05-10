package com.example.weeklyschedule.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weeklyschedule.data.local.daos.WeeklyScheduleDao
import com.example.weeklyschedule.data.local.entities.Breaks
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.data.local.entities.Days
import com.example.weeklyschedule.data.local.entities.WeeklySchedule

@Database(
    entities = [WeeklySchedule::class, Courses::class, Days::class,Breaks::class],
    version = 2,
    exportSchema = false
)
abstract class WeeklyScheduleRoomDataBase:RoomDatabase() {
    abstract val weeklyScheduleDao:WeeklyScheduleDao
    companion object{
        const val DATABASE_NAME="weekly_db"
    }

}