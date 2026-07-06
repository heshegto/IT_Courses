package com.bandeev.it_courses.domain.auth.repositories

import com.bandeev.it_courses.domain.auth.models.LogInResult
import com.bandeev.it_courses.domain.auth.models.LogInViaEmailData

interface LogInViaEmail {
    fun logIn(data: LogInViaEmailData): LogInResult
    fun ifDataIsInvalid(message: String)
    fun ifLogInFailed(message: String)
}