package com.bandeev.it_courses.all_courses.presentation

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

    init {
        loadAllCourses()
    }

    fun loadAllCourses(forceUpdate: Boolean = false) {
        if (uiState.value !is AllCoursesUiState.Success) {
            _uiState.value = AllCoursesUiState.Loading
        }
        viewModelScope.launch {
            if (uiState.value !is AllCoursesUiState.Success) {
                _uiState.value = AllCoursesUiState.Loading
            }
            try {
                val favouriteIds: List<Int> = getFavouriteIdsUseCase.execute()
                val result: CourseList =
                    CourseList(getAlCoursesUseCase.execute(forceUpdate).courses.map { course ->
                        course.copy(hasLike = favouriteIds.contains(course.id))
                    })

                if (result.isEmpty()) {
                    _uiState.value = AllCoursesUiState.Empty
                } else {
                    var currentSortOrder: SortOrder = SortOrder.NONE
                    var currentForcedUpdateCount: Int = 0
                    if (uiState.value is AllCoursesUiState.Success) {
                        currentSortOrder = (uiState.value as AllCoursesUiState.Success).sortOrder
                        currentForcedUpdateCount =
                            (uiState.value as AllCoursesUiState.Success).forcedUpdatesCount
                        if (forceUpdate) {
                            currentForcedUpdateCount++
                        }
                    }
                    val sortedCourses = if (forceUpdate) {
                        AllCoursesUiState.Success(
                            result,
                            forcedUpdatesCount = currentForcedUpdateCount
                        )
                    } else {
                        AllCoursesUiState.Success.getSortedCourses(
                            result,
                            currentSortOrder,
                            currentForcedUpdateCount
                        )
                    }
                    _uiState.value = sortedCourses
                }
            } catch (e: Exception) {
                _uiState.value = AllCoursesUiState.Error
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
        if (_uiState.value !is AllCoursesUiState.Success) return
        val newSortOrder: SortOrder =
            when ((uiState.value as AllCoursesUiState.Success).sortOrder) {
                SortOrder.NONE -> SortOrder.ASCENDING
                SortOrder.DESCENDING -> SortOrder.ASCENDING
                SortOrder.ASCENDING -> SortOrder.DESCENDING
            }
        _uiState.value = AllCoursesUiState.Success.getSortedCourses(
            (uiState.value as AllCoursesUiState.Success),
            newSortOrder
        )
    }
}
