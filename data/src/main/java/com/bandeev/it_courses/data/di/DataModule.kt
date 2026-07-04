package com.bandeev.it_courses.data.di

import com.bandeev.it_courses.data.auth.di.dataAuthModule
import com.bandeev.it_courses.data.network.di.networkDi
import org.koin.core.module.Module

val dataModule = arrayOf<Module>(
    dataAuthModule,
    networkDi
)