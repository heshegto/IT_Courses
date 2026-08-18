package com.bandeev.it_courses.data.auth.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.net.toUri
import com.bandeev.it_courses.domain.auth.models.ExternalAuthServices
import com.bandeev.it_courses.domain.auth.repositories.ExternalAuthNavigator
import com.bandeev.it_courses.data.BuildConfig

class ExternalAuthNavigatorImpl(val context: Context) : ExternalAuthNavigator {
    private val authUrls = mapOf(
        ExternalAuthServices.VK to BuildConfig.VK_URL,
        ExternalAuthServices.OK to BuildConfig.OK_URL,
    )
    override fun openUrl(authService: ExternalAuthServices, errorMessage: String) {
        val intent = Intent(Intent.ACTION_VIEW, authUrls[authService]?.toUri()).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            MyToastImpl(context).doToast(errorMessage)
            Log.d("login", "${errorMessage}. Error: ${e.message}")
        }
    }
}
