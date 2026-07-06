package com.bandeev.it_courses.favourite_courses.di

import com.bandeev.it_courses.favourite_courses.presentation.FavouriteCoursesViewModel
import org.koin.dsl.module

val favouriteCoursesModule = module {
    factory<FavouriteCoursesViewModel> {
        FavouriteCoursesViewModel(
            getFavouriteUseCase = get(),
            pushFavouriteUseCase = get()
        )
    }
}
