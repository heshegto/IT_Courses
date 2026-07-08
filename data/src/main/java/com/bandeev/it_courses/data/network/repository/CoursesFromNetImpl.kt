package com.bandeev.it_courses.data.network.repository

import com.bandeev.it_courses.domain.models.CourseList
import com.bandeev.it_courses.domain.network.repository.CoursesFromNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoursesFromNetImpl(val baseUrl: String, val pathUrl: String): CoursesFromNet {
    private var cachedCourse: CourseList? = null

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val productApi: ProductApi = retrofit.create(ProductApi::class.java)

    override suspend fun getAllCourses(): CourseList {
        if (cachedCourse == null) {
            cachedCourse = withContext(Dispatchers.IO) {
                productApi.getCourses(pathUrl)
            }
        }
        return cachedCourse!!
    }

    fun clearCache() {
        cachedCourse = null
    }
}
