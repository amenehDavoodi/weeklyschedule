package com.example.weeklyschedule.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.data.local.entities.WeeklySchedule
import com.example.weeklyschedule.domain.ListCourses
import kotlinx.coroutines.flow.Flow

@Dao
interface WeeklyScheduleDao {

    @Query("SELECT * FROM WeeklySchedule")
    fun getAllWeeklySchedule(): Flow<List<WeeklySchedule>>

    @Query("SELECT * FROM WeeklySchedule WHERE dayId = :id")
    fun observeADayById(id: Int): Flow<WeeklySchedule>

    @Query("SELECT c.courseName,c.coursePic FROM Courses c " +
            "INNER JOIN WeeklySchedule ws ON  c.courseId=ws.courseId " +
            "INNER JOIN Days ON Days.dayId=ws.dayId " +
            "INNER JOIN Breaks ON Breaks.id=ws.breakId WHERE dayId = :id ORDER BY Breaks.id ASC" )
    fun observeCoursesOfADayById(id: Int): Flow<List<Courses>>

    @Query("SELECT * FROM Courses")
    fun observeAllCourse(): Flow<ListCourses>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weeklySchedule: WeeklySchedule)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Courses)

//    @Delete
//    suspend fun deleteADay(id:Int)

    @Update
    suspend fun updateADay(weeklySchedule: WeeklySchedule)

}