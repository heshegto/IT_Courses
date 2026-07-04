package com.bandeev.it_courses.domain.storage.usecases

import com.bandeev.it_courses.domain.models.CourseList
import com.bandeev.it_courses.domain.storage.repository.CourseLocalStorage

class GetFavouriteUseCase(val localStorage: CourseLocalStorage) {
    suspend fun execute(): CourseList {
        return localStorage.readAll()
    }
}