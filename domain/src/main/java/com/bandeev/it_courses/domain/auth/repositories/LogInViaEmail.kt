package com.bandeev.it_courses.domain.auth.repositories

import com.bandeev.it_courses.domain.auth.models.LogInViaEmailData

interface LogInViaEmail {
    fun logIn(data: LogInViaEmailData): Boolean
    fun ifDataIsInvalid(message: String)
    fun ifLogInFailed(message: String)
    fun ifLogInSucceed()
}