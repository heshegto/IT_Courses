package com.bandeev.it_courses.domain.auth.repositories

interface ExternalAuthNavigator {
    fun openUrl(url: String, errorMessage: String)
}