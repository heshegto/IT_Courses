package com.bandeev.it_courses.domain.auth.usecases

import com.bandeev.it_courses.domain.auth.models.LogInViaEmailData
import com.bandeev.it_courses.domain.auth.repositories.LogInViaEmail

class LogInUseCase(val logInClass: LogInViaEmail) {
    fun execute(data: LogInViaEmailData, loginFailedMessage: String, invalidDataMessage: String){
        if (data.isValid()) {
            if (logInClass.logIn(data)) {
                logInClass.ifLogInSucceed()
            } else {
                logInClass.ifLogInFailed(loginFailedMessage)
            }
        } else {
            logInClass.ifDataIsInvalid(invalidDataMessage)
        }
    }
}