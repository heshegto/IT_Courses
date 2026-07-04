package com.bandeev.it_courses.data.storage.di

import androidx.room.Room
import com.bandeev.it_courses.data.storage.repository.CourseDao
import com.bandeev.it_courses.data.storage.repository.CourseDatabase
import com.bandeev.it_courses.data.storage.repository.CourseLocalStorageImpl
import com.bandeev.it_courses.domain.storage.repository.CourseLocalStorage
import org.koin.dsl.module

internal val storageDi = module {
    single<CourseDatabase> {
        Room.databaseBuilder(
            get(),
            CourseDatabase::class.java,
            "favourite_courses_db").build()
    }
    single<CourseDao> { get<CourseDatabase>().getDao() }
    single<CourseLocalStorage> { CourseLocalStorageImpl(get()) }
}