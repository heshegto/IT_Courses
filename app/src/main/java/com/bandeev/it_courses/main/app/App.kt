package com.bandeev.it_courses.main.app

import android.app.Application
import com.bandeev.it_courses.all_courses.di.allCoursesModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

import com.bandeev.it_courses.data.di.dataModule
import com.bandeev.it_courses.domain.di.domainModule
import com.bandeev.it_courses.authentification.di.authModule
import com.bandeev.it_courses.course.di.courseModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                *dataModule,
                *domainModule,
                authModule,
                allCoursesModule,
                courseModule
            )
        }
    }
}