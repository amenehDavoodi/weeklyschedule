package com.example.weeklyschedule.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Courses(
    @PrimaryKey
    val courseId: Int,
    val courseName: String,
    val coursePic: Int
){
    fun toCourses()=Courses(courseId,courseName,coursePic)
}
