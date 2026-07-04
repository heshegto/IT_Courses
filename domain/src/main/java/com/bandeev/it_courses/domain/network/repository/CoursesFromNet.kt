package com.bandeev.it_courses.domain.network.repository

import com.bandeev.it_courses.domain.models.CourseList

interface CoursesFromNet {
    suspend fun getAllCourses(): CourseList
}