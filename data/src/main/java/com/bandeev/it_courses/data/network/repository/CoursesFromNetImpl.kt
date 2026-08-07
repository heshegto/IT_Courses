package com.bandeev.it_courses.data.network.repository

import com.bandeev.it_courses.domain.models.CourseList
import com.bandeev.it_courses.domain.network.repository.CoursesFromNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CoursesFromNetImpl(val baseUrl: String, val pathUrl: String): CoursesFromNet {
    private var cachedCourse: CourseList? = null
    private var lastUpdateTime: Long = 0
    private val cacheDuration = 5 * 60 * 1000
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val productApi: ProductApi = retrofit.create(ProductApi::class.java)

    override suspend fun getAllCourses(forceUpdate: Boolean): CourseList {
        cacheValidation(forceUpdate)
        if (cachedCourse == null) {
            cachedCourse = withContext(Dispatchers.IO) {
                productApi.getCourses(pathUrl)
            }
        }
        return cachedCourse!!
    }

    private fun clearCache() {
        cachedCourse = null
    }

    private fun cacheValidation(forceUpdate: Boolean = false) {
        if (System.currentTimeMillis() - lastUpdateTime > cacheDuration || forceUpdate) {
            lastUpdateTime = System.currentTimeMillis()
            clearCache()
        }
    }
}
