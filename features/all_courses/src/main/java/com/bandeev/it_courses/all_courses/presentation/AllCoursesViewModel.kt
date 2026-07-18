package com.bandeev.it_courses.all_courses.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bandeev.it_courses.domain.models.Course
import com.bandeev.it_courses.domain.models.CourseList
import com.bandeev.it_courses.domain.network.usecases.GetAllCoursesUseCase
import com.bandeev.it_courses.domain.storage.usecases.GetFavouriteIdsUseCase
import com.bandeev.it_courses.domain.storage.usecases.PushFavouriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllCoursesViewModel(
    val getAlCoursesUseCase: GetAllCoursesUseCase,
    val pushFavouriteUseCase: PushFavouriteUseCase,
    val getFavouriteIdsUseCase: GetFavouriteIdsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<AllCoursesUiState?>(null)
    val uiState: StateFlow<AllCoursesUiState?> = _uiState.asStateFlow()

    private var currentSortOrder = SortOrder.NONE

    init {
        loadAllCourses()
    }

    fun loadAllCourses() {
        if (_uiState.value == null) {
            _uiState.value = AllCoursesUiState.Loading
        }
        viewModelScope.launch {
            try {
                val favouriteIds = getFavouriteIdsUseCase.execute()
                val result = getAlCoursesUseCase.execute().courses.map { course ->
                    course.copy(hasLike = favouriteIds.contains(course.id))
                }
                _uiState.value = AllCoursesUiState.Success(sortCourses(CourseList(result)))
            } catch (e: Exception) {
                _uiState.value = AllCoursesUiState.Error
                Log.e("AllCoursesViewModel", "Error loading courses", e)
            }
        }
    }

    fun onFavouriteClicked(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            pushFavouriteUseCase.execute(course)
            loadAllCourses()
        }
    }

    fun onSortCoursesClicked() {
        currentSortOrder = when (currentSortOrder) {
            SortOrder.NONE -> SortOrder.ASCENDING
            SortOrder.DESCENDING -> SortOrder.ASCENDING
            SortOrder.ASCENDING -> SortOrder.DESCENDING
        }
        _uiState.value = AllCoursesUiState.Success(sortCourses((uiState.value as AllCoursesUiState.Success).courses))
    }

    private fun sortCourses(courses: CourseList): CourseList {
        return when (currentSortOrder) {
            SortOrder.ASCENDING -> {
                CourseList(courses.courses.sortedBy { it.publishDate })
            }
            SortOrder.DESCENDING -> {
                CourseList(courses.courses.sortedByDescending { it.publishDate })
            }
            SortOrder.NONE -> {
                courses
            }
        }
    }
}
