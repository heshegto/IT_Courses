package com.bandeev.it_courses.data.di

import com.bandeev.it_courses.data.auth.di.dataAuthModule
import org.koin.core.module.Module

val dataModule = arrayOf<Module>(
    dataAuthModule,
)