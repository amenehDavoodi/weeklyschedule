package com.example.weeklyschedule.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weeklyschedule.data.local.Courses
import com.example.weeklyschedule.data.local.Days
import com.example.weeklyschedule.data.local.WeeklySchedule
import com.example.weeklyschedule.data.local.daos.WeeklyScheduleDao

@Database(entities = arrayOf(
    WeeklySchedule::class,
    Courses::class,
    Days::class
), version = 1, exportSchema = false)
abstract class WeeklyScheduleRoomDataBase:RoomDatabase() {
    abstract fun weeklySchedule(): WeeklyScheduleDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: WeeklyScheduleRoomDataBase? = null

        fun getDatabase(context: Context): WeeklyScheduleRoomDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeeklyScheduleRoomDataBase::class.java,
                    "weekly_schedule_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}