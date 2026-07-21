package com.bandeev.it_courses.domain.models

/**
 * Data model to contain several courses and functions to work with them
 *
 * @property courses List of courses
 */
data class CourseList(
    val courses: List<Course>
) {
    val size = courses.size
    companion object {
        fun getEmpty(): CourseList = CourseList(emptyList())
    }

    operator fun get(index: Int): Course = courses[index]

    operator fun contains(course: Course): Boolean = courses.contains(course)
}