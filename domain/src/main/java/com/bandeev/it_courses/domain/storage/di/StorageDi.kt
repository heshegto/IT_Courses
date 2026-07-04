package com.bandeev.it_courses.domain.storage.di

import com.bandeev.it_courses.domain.storage.usecases.GetFavouriteIdsUseCase
import com.bandeev.it_courses.domain.storage.usecases.GetFavouriteUseCase
import com.bandeev.it_courses.domain.storage.usecases.PushFavouriteUseCase
import org.koin.dsl.module

internal val storageDi = module {
    factory<GetFavouriteIdsUseCase> { GetFavouriteIdsUseCase(localStorage = get()) }
    factory<GetFavouriteUseCase> { GetFavouriteUseCase(localStorage = get()) }
    factory<PushFavouriteUseCase> { PushFavouriteUseCase(localStorage = get()) }
}