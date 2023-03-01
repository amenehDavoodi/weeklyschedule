package com.example.weeklyschedule.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.data.local.entities.WeeklySchedule
import kotlinx.coroutines.flow.Flow

@Dao
interface WeeklyScheduleDao {

    @Query("SELECT * FROM WeeklySchedule")
    fun getAllWeeklySchedule(): Flow<List<WeeklySchedule>>

    @Query("SELECT * FROM WeeklySchedule WHERE dayId = :id")
    fun observeADayById(id: Int): Flow<WeeklySchedule>

    @Query("SELECT * FROM Courses")
    fun observeAllCourse(): Flow<List<Courses>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weeklySchedule: WeeklySchedule)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Courses)

//    @Delete
//    suspend fun deleteADay(id:Int)

    @Update
    suspend fun updateADay(weeklySchedule: WeeklySchedule)

}