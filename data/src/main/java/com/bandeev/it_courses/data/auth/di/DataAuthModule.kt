package com.bandeev.it_courses.data.auth.di

import com.bandeev.it_courses.data.auth.repository.ExternalAuthNavigatorImpl
import com.bandeev.it_courses.data.auth.repository.LogInViaEmailImpl
import com.bandeev.it_courses.data.auth.repository.MyToastImpl
import com.bandeev.it_courses.domain.auth.repositories.ExternalAuthNavigator
import com.bandeev.it_courses.domain.auth.repositories.LogInViaEmail
import com.bandeev.it_courses.domain.auth.repositories.MyToast
import org.koin.dsl.module

internal val dataAuthModule = module {
    single<LogInViaEmail> { LogInViaEmailImpl(context = get()) }
    single<ExternalAuthNavigator> { ExternalAuthNavigatorImpl(context = get()) }
    single<MyToast> { MyToastImpl(context = get()) }
}