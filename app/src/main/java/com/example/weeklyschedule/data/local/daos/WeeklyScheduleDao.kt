package com.example.weeklyschedule.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weeklyschedule.data.local.WeeklySchedule

@Dao
interface WeeklyScheduleDao {

    @Query("SELECT * FROM WeeklySchedule")
   suspend fun getAllWeeklySchedule(): LiveData<List<WeeklySchedule>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weeklySchedule:WeeklySchedule)

    @Delete()
    suspend fun deleteADay()

    @Update
    suspend fun updateADay()

}