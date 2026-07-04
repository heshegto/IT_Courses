package com.bandeev.it_courses.domain.network.di

import com.bandeev.it_courses.domain.network.usecases.GetAllCoursesUseCase
import org.koin.dsl.module

internal val networkDi = module {
        factory<GetAllCoursesUseCase> { GetAllCoursesUseCase(coursesFromNet = get()) }
    }
