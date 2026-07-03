package com.bandeev.it_courses.authentification.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bandeev.it_courses.domain.auth.models.LogInViaEmailData
import com.bandeev.it_courses.domain.auth.usecases.AuthWithOKUseCase
import com.bandeev.it_courses.domain.auth.usecases.AuthWithVKUseCase
import com.bandeev.it_courses.domain.auth.usecases.ForgotPasswordUseCase
import com.bandeev.it_courses.domain.auth.usecases.LogInUseCase
import com.bandeev.it_courses.domain.auth.usecases.SignUpUseCase

class AuthViewModel(
    application: Application,
    val logUseCase: LogInUseCase,
    val signUpUseCase: SignUpUseCase,
    val forgotPasswordUseCase: ForgotPasswordUseCase,
    val authWithOKUseCase: AuthWithOKUseCase,
    val authWithVKUseCase: AuthWithVKUseCase
) : AndroidViewModel(application) {
    var logInActivityDataState: LogInViaEmailData? = null

    fun clickLogIn(logInFailedMessage: String, invalidLogInDataMessage: String) {
        logInActivityDataState?.let {
            logUseCase.execute(it, logInFailedMessage, invalidLogInDataMessage)
        }
    }

    fun clickSignUp() {
        signUpUseCase.execute()
    }

    fun clickForgotPassword() {
        forgotPasswordUseCase.execute()
    }

    fun clickAuthVK(errorMessage: String) {
        authWithVKUseCase.execute(errorMessage)
    }

    fun clickAuthOK(errorMessage: String) {
        authWithOKUseCase.execute(errorMessage)
    }
}
