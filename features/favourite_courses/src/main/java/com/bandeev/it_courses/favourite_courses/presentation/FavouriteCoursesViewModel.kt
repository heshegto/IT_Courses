package com.bandeev.it_courses.favourite_courses.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.bandeev.it_courses.domain.models.Course
import com.bandeev.it_courses.domain.models.CourseList
import com.bandeev.it_courses.domain.storage.usecases.GetFavouriteUseCase
import com.bandeev.it_courses.domain.storage.usecases.PushFavouriteUseCase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavouriteCoursesViewModel(
    val getFavouriteUseCase: GetFavouriteUseCase,
    val pushFavouriteUseCase: PushFavouriteUseCase
) : ViewModel() {

    private val _courses = MutableStateFlow(CourseList.getEmpty())
    val courses: StateFlow<CourseList> = _courses.asStateFlow()

    init {
        loadFavouriteCourses()
    }

    fun loadFavouriteCourses() {
        viewModelScope.launch {
            try {
                val result = getFavouriteUseCase.execute()
                _courses.value = result
            } catch (e: Exception) {
                Log.e("FavouriteCoursesViewModel", "Error loading courses", e)
            }
        }
    }

    fun onFavouriteClicked(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            pushFavouriteUseCase.execute(course)
            loadFavouriteCourses()
        }
    }
}