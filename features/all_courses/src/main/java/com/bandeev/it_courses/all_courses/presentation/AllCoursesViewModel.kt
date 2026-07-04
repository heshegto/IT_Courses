package com.bandeev.it_courses.all_courses.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bandeev.it_courses.domain.models.Course
import com.bandeev.it_courses.domain.models.CourseList
import com.bandeev.it_courses.domain.network.repository.CoursesFromNet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllCoursesViewModel(
    val coursesRepository: CoursesFromNet
) : ViewModel() {

    private val _courses = MutableStateFlow(CourseList.getEmpty())
    val courses: StateFlow<CourseList> = _courses.asStateFlow()
    private var isSorted = false

    init {
        loadAllCourses()
    }

    fun loadAllCourses() {
        viewModelScope.launch {
            try {
                _courses.value = coursesRepository.getAllCourses()
            } catch (e: Exception) {
                Log.e("AllCoursesViewModel", "Error loading courses", e)
            } finally {
            }
        }
    }

    fun onFavouriteClicked(course: Course) {

    }

    fun sortCourses() {
        isSorted = !isSorted
        val currentList = _courses.value.courses
        val sortedList = if (isSorted) {
            currentList.sortedBy { it.publishDate }
        } else {
            currentList.sortedByDescending { it.publishDate }
        }
        _courses.value = CourseList(sortedList)
    }
}
