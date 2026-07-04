package com.bandeev.it_courses.data.storage.repository

import com.bandeev.it_courses.data.storage.models.CourseEntity
import com.bandeev.it_courses.domain.models.Course
import com.bandeev.it_courses.domain.models.CourseList
import com.bandeev.it_courses.domain.storage.repository.CourseLocalStorage

class CourseLocalStorageImpl(db: CourseDatabase): CourseLocalStorage {
    private val courseDao = db.getDao()

    override suspend fun readAll(): CourseList {
        return CourseList(courseDao.selectAll().map { it.fromEntityToCourse() })
    }

    override suspend fun readIds(): List<Int> {
        return courseDao.selectAllIds()
    }

    override suspend fun add(course: Course) {
        courseDao.insert(CourseEntity.fromCourseToEntity(course))
    }

    override suspend fun delete(course: Course) {
        courseDao.delete(CourseEntity.fromCourseToEntity(course))
    }

    override suspend fun contains(course: Course): Boolean {
        return contains(course.id)
    }

    override suspend fun contains(id: Int): Boolean {
        return id in readIds()
    }
}