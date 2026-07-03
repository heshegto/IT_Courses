package com.bandeev.it_courses.domain.di


import com.bandeev.it_courses.domain.auth.di.domainAuthModule
import org.koin.core.module.Module

val domainModule = arrayOf<Module>(
    domainAuthModule,
)