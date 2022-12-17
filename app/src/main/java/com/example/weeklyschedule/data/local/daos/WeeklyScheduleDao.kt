package com.example.weeklyschedule.data.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weeklyschedule.data.local.WeeklySchedule
import kotlinx.coroutines.flow.Flow

@Dao
interface WeeklyScheduleDao {

    @Query("SELECT * FROM WeeklySchedule")
   fun getAllWeeklySchedule(): Flow<List<WeeklySchedule>>

    @Query("SELECT * FROM WeeklySchedule WHERE dayId = :id")
    fun observeADayById(id: Int): Flow<WeeklySchedule>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weeklySchedule:WeeklySchedule)

    @Delete
    suspend fun deleteADay(id:Int)

    @Update
    suspend fun updateADay(weeklySchedule:WeeklySchedule)


}