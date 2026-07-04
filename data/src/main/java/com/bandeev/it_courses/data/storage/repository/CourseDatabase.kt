package com.bandeev.it_courses.data.storage.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bandeev.it_courses.data.storage.models.CourseEntity

@Database(entities = [CourseEntity::class], version = 1)
abstract class CourseDatabase : RoomDatabase() {
    abstract fun getDao(): CourseDao
}