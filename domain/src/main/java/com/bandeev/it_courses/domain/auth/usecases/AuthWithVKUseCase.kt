package com.bandeev.it_courses.domain.auth.usecases

import com.bandeev.it_courses.domain.auth.repositories.AuthNavigator

class AuthWithVKUseCase(val navigator: AuthNavigator) {
    fun execute(errorMessage: String) {
        navigator.openUrl("https://vk.com", errorMessage)
    }
}