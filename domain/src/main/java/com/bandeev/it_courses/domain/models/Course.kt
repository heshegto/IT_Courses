package com.bandeev.it_courses.domain.models

/**
 * Data model for course and it's details
 *
 * @property id Unique course id
 * @property title Name of course
 * @property text Detailed description of course
 * @property price Price, that author set for this course
 * @property rate Rating. Shows how people have rated this course
 * @property startDate Start date
 * @property hasLike Shows if course is in favourites for current user
 * @property publishDate Publish date. Not shown to user, but used for sorting
 */
data class Course (
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String,
) {
    override fun toString(): String {
        return "Course(id=$id, title='$title', hasLike=$hasLike)"
    }
}