package com.bandeev.it_courses.domain.auth.repositories

interface AuthNavigator {
    fun openUrl(url: String, errorMessage: String)
}