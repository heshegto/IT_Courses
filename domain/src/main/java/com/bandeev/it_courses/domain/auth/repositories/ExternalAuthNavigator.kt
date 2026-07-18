package com.bandeev.it_courses.domain.auth.repositories

import com.bandeev.it_courses.domain.auth.models.ExternalAuthServices

interface ExternalAuthNavigator {
    fun openUrl(authService: ExternalAuthServices, errorMessage: String)
}