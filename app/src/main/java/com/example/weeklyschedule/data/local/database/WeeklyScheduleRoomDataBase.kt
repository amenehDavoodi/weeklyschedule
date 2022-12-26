package com.example.weeklyschedule.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.data.local.entities.Days
import com.example.weeklyschedule.data.local.entities.WeeklySchedule

@Database(
    entities = [WeeklySchedule::class, Courses::class, Days::class],
    version = 1,
    exportSchema = false
)
abstract class WeeklyScheduleRoomDataBase:RoomDatabase() {

}