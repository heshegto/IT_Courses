package com.bandeev.it_courses.domain.storage.usecases

import com.bandeev.it_courses.domain.models.Course
import com.bandeev.it_courses.domain.storage.repository.CourseLocalStorage

class PushFavouriteUseCase(val localStorage: CourseLocalStorage) {
    suspend fun execute(course: Course) {
        if (localStorage.contains(course)) {
            delete(course)
        } else {
             add(course)
        }
    }

    private suspend fun add(course: Course) {
        localStorage.add(course.copy(hasLike = true))
    }
    private suspend fun delete(course: Course) {
        localStorage.delete(course)
    }

}