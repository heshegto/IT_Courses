package com.bandeev.it_courses.domain.auth.usecases

import com.bandeev.it_courses.domain.auth.models.ExternalAuthServices
import com.bandeev.it_courses.domain.auth.repositories.ExternalAuthNavigator

class AuthWithOKUseCase(val navigator: ExternalAuthNavigator) {
    fun execute(errorMessage: String){
        navigator.openUrl(ExternalAuthServices.OK, errorMessage)
    }
}