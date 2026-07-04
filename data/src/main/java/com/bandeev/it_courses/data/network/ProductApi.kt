package com.bandeev.it_courses.data.network

import com.bandeev.it_courses.domain.models.CourseList
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductApi {
    @GET()
    suspend fun getCourses(
        @Url url: String
    ): CourseList
}