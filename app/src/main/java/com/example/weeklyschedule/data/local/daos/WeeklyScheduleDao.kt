package com.example.weeklyschedule.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weeklyschedule.data.local.WeeklySchedule

@Dao
interface WeeklyScheduleDao {

    @Query("SELECT * FROM WeeklySchedule")
    fun getAllWeeklySchedule(): LiveData<List<WeeklySchedule>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(weeklySchedule:WeeklySchedule)
}