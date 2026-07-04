package com.bandeev.it_courses.domain.storage.usecases

import com.bandeev.it_courses.domain.storage.repository.CourseLocalStorage

class GetFavouriteIdsUseCase(val localStorage: CourseLocalStorage) {
    suspend fun execute(): List<Int> {
        return localStorage.readIds()
    }
}