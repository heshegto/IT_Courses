package com.bandeev.it_courses.domain.storage.repository

import com.bandeev.it_courses.domain.models.Course
import com.bandeev.it_courses.domain.models.CourseList

interface CourseLocalStorage {
    suspend fun readAll(): CourseList
    suspend fun readIds(): List<Int>
    suspend fun add(course: Course)
    suspend fun delete(course: Course)
    suspend fun contains(course: Course): Boolean
    suspend fun contains(id: Int): Boolean
}
