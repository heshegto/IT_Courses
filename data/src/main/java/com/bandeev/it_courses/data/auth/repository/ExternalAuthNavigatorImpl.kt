package com.bandeev.it_courses.data.auth.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.net.toUri
import com.bandeev.it_courses.domain.auth.models.ExternalAuthServices
import com.bandeev.it_courses.domain.auth.repositories.ExternalAuthNavigator

class ExternalAuthNavigatorImpl(val context: Context) : ExternalAuthNavigator {
    private val authUrls = mapOf(
        ExternalAuthServices.VK to "https://vk.com",
        ExternalAuthServices.OK to "https://ok.ru",
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
