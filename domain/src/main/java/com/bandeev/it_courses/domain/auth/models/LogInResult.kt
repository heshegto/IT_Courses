package com.bandeev.it_courses.domain.auth.models

import java.net.HttpCookie

data class LogInResult(val cookie: HttpCookie?, val isLoggedIn: Boolean)
