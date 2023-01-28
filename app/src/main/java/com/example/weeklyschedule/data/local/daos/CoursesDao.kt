package com.example.weeklyschedule.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weeklyschedule.data.local.entities.Courses
import com.example.weeklyschedule.data.local.entities.WeeklySchedule
import kotlinx.coroutines.flow.Flow

@Dao
interface CoursesDao {
    @Query("SELECT * FROM Courses")
    fun getAllCourse(): Flow<List<Courses>>

    @Query("SELECT * FROM Courses WHERE courseId = :id")
    fun observeCourseById(id: Int): Flow<WeeklySchedule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Courses)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCourses()
    {
//        for (i to DaysList.size){}
    }
}