package com.bandeev.it_courses.authentification.di

import com.bandeev.it_courses.authentification.presentation.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel<AuthViewModel> {
        AuthViewModel(
            application = get(),
            logUseCase = get(),
            signUpUseCase = get(),
            forgotPasswordUseCase = get(),
            authWithOKUseCase = get(),
            authWithVKUseCase = get()
        )
    }
}