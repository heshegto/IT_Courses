package com.bandeev.it_courses.all_courses.di

import com.bandeev.it_courses.all_courses.presentation.AllCoursesViewModel
import org.koin.dsl.module

val allCoursesModule = module {
    factory<AllCoursesViewModel> {
        AllCoursesViewModel(
            coursesRepository = get()
        )
    }
}
