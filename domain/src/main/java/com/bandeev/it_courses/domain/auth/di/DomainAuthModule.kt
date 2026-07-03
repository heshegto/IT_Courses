package com.bandeev.it_courses.domain.auth.di

import com.bandeev.it_courses.domain.auth.usecases.AuthWithOKUseCase
import com.bandeev.it_courses.domain.auth.usecases.AuthWithVKUseCase
import com.bandeev.it_courses.domain.auth.usecases.ForgotPasswordUseCase
import com.bandeev.it_courses.domain.auth.usecases.LogInUseCase
import com.bandeev.it_courses.domain.auth.usecases.SignUpUseCase
import org.koin.dsl.module

internal val domainAuthModule = module {
    factory<AuthWithOKUseCase> { AuthWithOKUseCase(navigator = get()) }
    factory<AuthWithVKUseCase> { AuthWithVKUseCase(navigator = get()) }
    factory<ForgotPasswordUseCase> { ForgotPasswordUseCase(myToast = get()) }
    factory<LogInUseCase> { LogInUseCase(logInClass = get()) }
    factory<SignUpUseCase> { SignUpUseCase(myToast = get()) }
}