package com.bandeev.it_courses.course.di

import com.bandeev.it_courses.course.presentation.CourseAdapter
import com.bandeev.it_courses.domain.models.CourseList

import org.koin.dsl.module

val courseModule = module {
    factory { onFav ->
        CourseAdapter(
            dataSet = CourseList.getEmpty(),
            onFavouriteClick = onFav.get()
        )
    }
}