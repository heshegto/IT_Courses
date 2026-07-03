package com.bandeev.it_courses.domain.auth.usecases

import com.bandeev.it_courses.domain.auth.repositories.AuthNavigator

class AuthWithOKUseCase(val navigator: AuthNavigator) {
    fun execute(errorMessage: String){
        navigator.openUrl("https://ok.ru", errorMessage)
    }
}