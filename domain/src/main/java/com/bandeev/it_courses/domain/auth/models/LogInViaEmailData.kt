package com.bandeev.it_courses.domain.auth.models

data class LogInViaEmailData( val email: String, val password: String) {

    fun isValid(): Boolean {
        return isEmailValid(email) && isPasswordValid(password)
    }
    private fun isEmailValid(email: String) : Boolean {
        return email.isNotEmpty() &&
                Regex("^[A-Za-z0-9._-]+@[A-Za-z0-9._-]+\\.[A-Za-z]{2,}$")
                    .matches(email)
    }
    private fun isPasswordValid(password: String) : Boolean {
        return password.isNotEmpty()
    }
}
