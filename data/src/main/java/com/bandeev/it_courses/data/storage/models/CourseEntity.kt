package com.bandeev.it_courses.data.storage.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bandeev.it_courses.domain.models.Course

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "rate") val rate: String,
    @ColumnInfo(name = "startDate") val startDate: String,
    @ColumnInfo(name = "hasLike") val hasLike: Boolean,
    @ColumnInfo(name = "publishDate") val publishDate: String,
) {
    constructor (course: Course) : this(
        id = course.id,
        title = course.title,
        text = course.text,
        price = course.price,
        rate = course.rate,
        startDate = course.startDate,
        hasLike = course.hasLike,
        publishDate = course.publishDate,
    )

    companion object {
        fun fromCourseToEntity(course: Course): CourseEntity {
            return CourseEntity(course)
        }
        fun fromEntityToCourse(courseEntity: CourseEntity): Course {
            return courseEntity.fromEntityToCourse()
        }
    }

    fun fromEntityToCourse(): Course {
        return Course(
            id = this.id,
            title = this.title,
            text = this.text,
            price = this.price,
            rate = this.rate,
            startDate = this.startDate,
            hasLike = this.hasLike,
            publishDate = this.publishDate,
        )
    }
}