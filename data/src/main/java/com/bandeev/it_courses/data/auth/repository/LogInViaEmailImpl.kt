package com.bandeev.it_courses.data.auth.repository

import android.content.Context
import com.bandeev.it_courses.domain.auth.models.LogInResult
import com.bandeev.it_courses.domain.auth.models.LogInViaEmailData
import com.bandeev.it_courses.domain.auth.repositories.LogInViaEmail
import java.net.HttpCookie

class LogInViaEmailImpl(val context: Context) : LogInViaEmail {
    /**
     * Log in via email and password. Save cookie if log in was successful
     * @param data - data for log in (email, password)
     * @return access token and log in result
     */
    //  At this development point just returns true. Doesn't save cookie.
    override fun logIn(data: LogInViaEmailData): LogInResult {
        // Instead of this line must be logic of login:
        return LogInResult(HttpCookie("logIn", "successful"), true)
    }

    override fun ifLogInFailed(message: String) {
        MyToastImpl(context).doToast(message)
    }

    override fun ifDataIsInvalid(message: String) {
        MyToastImpl(context).doToast(message)
    }
}