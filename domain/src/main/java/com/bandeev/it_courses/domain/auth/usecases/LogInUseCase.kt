package com.bandeev.it_courses.domain.auth.usecases

import com.bandeev.it_courses.domain.auth.models.LogInResult
import com.bandeev.it_courses.domain.auth.models.LogInViaEmailData
import com.bandeev.it_courses.domain.auth.repositories.LogInViaEmail

class LogInUseCase(val logInClass: LogInViaEmail) {
    fun execute(data: LogInViaEmailData, loginFailedMessage: String, invalidDataMessage: String): LogInResult{
        var result = LogInResult(null, false)
        if (data.isValid()) {
            result = logInClass.logIn(data)
            if (!result.isLoggedIn) {
                logInClass.ifLogInFailed(loginFailedMessage)
            }
        } else {
            logInClass.ifDataIsInvalid(invalidDataMessage)
        }
        return result
    }
}