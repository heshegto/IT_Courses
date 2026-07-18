package com.bandeev.it_courses.all_courses.presentation

import com.bandeev.it_courses.domain.models.CourseList

sealed class AllCoursesUiState {
    object Loading : AllCoursesUiState()
    object Error : AllCoursesUiState()
    data class Success(val courses: CourseList) : AllCoursesUiState()
}
