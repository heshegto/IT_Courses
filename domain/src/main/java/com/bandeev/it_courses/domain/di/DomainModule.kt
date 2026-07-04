package com.bandeev.it_courses.domain.di

import com.bandeev.it_courses.domain.network.usecases.GetAllCoursesUseCase
import com.bandeev.it_courses.domain.storage.usecases.GetFavouriteIdsUseCase
import com.bandeev.it_courses.domain.storage.usecases.GetFavouriteUseCase
import com.bandeev.it_courses.domain.storage.usecases.PushFavouriteUseCase
import com.bandeev.it_courses.domain.auth.di.domainAuthModule
import com.bandeev.it_courses.domain.network.di.networkDi
import org.koin.core.module.Module
import org.koin.dsl.module

val domainModule = arrayOf<Module>(
    domainAuthModule,
    networkDi
)
