package com.bandeev.it_courses.domain.network.usecases

import com.bandeev.it_courses.domain.models.CourseList
import com.bandeev.it_courses.domain.network.repository.CoursesFromNet

class GetAllCoursesUseCase(val coursesFromNet: CoursesFromNet) {
    suspend fun execute(): CourseList {
        return coursesFromNet.getAllCourses()
    }
}