package com.bandeev.it_courses.data.network.di

import com.bandeev.it_courses.data.network.repository.CoursesFromNetImpl
import com.bandeev.it_courses.domain.network.repository.CoursesFromNet
import org.koin.dsl.module

internal val networkDi = module {
    single<CoursesFromNet> { CoursesFromNetImpl() }
}