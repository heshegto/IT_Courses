package com.bandeev.it_courses.data.storage.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bandeev.it_courses.data.storage.models.CourseEntity

@Dao
interface CourseDao {
    @Query("SELECT * FROM courses")
    suspend fun selectAll(): List<CourseEntity>

    @Query("SELECT id FROM courses")
    suspend fun selectAllIds(): List<Int>

    @Insert
    suspend fun insert(course: CourseEntity)

    @Delete
    suspend fun delete(course: CourseEntity)
}