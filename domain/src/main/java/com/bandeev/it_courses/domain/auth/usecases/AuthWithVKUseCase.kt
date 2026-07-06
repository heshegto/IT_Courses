package com.bandeev.it_courses.domain.auth.usecases

import com.bandeev.it_courses.domain.auth.repositories.ExternalAuthNavigator

class AuthWithVKUseCase(val navigator: ExternalAuthNavigator) {
    fun execute(errorMessage: String) {
        navigator.openUrl("https://vk.com", errorMessage)
    }
}