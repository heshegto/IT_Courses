package com.bandeev.it_courses.data.auth.repository

import android.content.Context
import android.content.Intent
import com.bandeev.it_courses.domain.auth.models.LogInResult
import com.bandeev.it_courses.domain.auth.models.LogInViaEmailData
import com.bandeev.it_courses.domain.auth.repositories.LogInViaEmail
import java.net.HttpCookie

class LogInViaEmailImpl(val context: Context) : LogInViaEmail {
    /**
     * Log in via email and password. Save cookie if log in was successful
     * @param data - data for log in (email, password)
     * @return true if log in was successful
     */
    //  At this development point just returns true. Doesn't save cookie.
    override fun logIn(data: LogInViaEmailData): Boolean {
        // Instead of this line must be logic of login:
        val result = LogInResult(HttpCookie("logIn", "successful"), true)
        return result.isLoggedIn
    }

    override fun ifLogInSucceed() {
        // TODO: Logic to return to main activity with fragment all_courses
        // val intent = Intent("com.bandeev.ACTION_MAIN")
        // context.startActivity(intent)
    }

    override fun ifLogInFailed(message: String) {
        MyToastImpl(context).doToast(message)
    }

    override fun ifDataIsInvalid(message: String) {
        MyToastImpl(context).doToast(message)
    }
}