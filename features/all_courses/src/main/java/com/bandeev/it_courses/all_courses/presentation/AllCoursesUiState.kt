package com.bandeev.it_courses.all_courses.presentation

import com.bandeev.it_courses.domain.models.CourseList

sealed class AllCoursesUiState {
    object Loading : AllCoursesUiState()
    object Error : AllCoursesUiState()
    object Empty : AllCoursesUiState()
    data class Success(
        val courses: CourseList,
        val sortOrder: SortOrder = SortOrder.NONE,
        val forcedUpdatesCount: Int = 0
    ) : AllCoursesUiState() {
        companion object {
            private fun sortCourses(courses: CourseList, newSortOrder: SortOrder): CourseList {
                return when (newSortOrder) {
                    SortOrder.ASCENDING -> {
                        CourseList(courses.courses.sortedBy { it.publishDate })
                    }

                    SortOrder.DESCENDING -> {
                        CourseList(courses.courses.sortedByDescending { it.publishDate })
                    }

                    SortOrder.NONE -> courses
                }
            }

            fun getSortedCourses(
                courses: CourseList,
                newSortOrder: SortOrder,
                forcedUpdatesCount: Int
            ): Success {
                return Success(
                    sortCourses(courses, newSortOrder),
                    newSortOrder,
                    forcedUpdatesCount
                )
            }

            fun getSortedCourses(oldData: Success, newSortOrder: SortOrder): Success {
                return oldData.copy(
                    courses = sortCourses(oldData.courses, newSortOrder),
                    sortOrder = newSortOrder
                )
            }
        }
    }
}
