package com.bandeev.it_courses.data.auth.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.net.toUri
import com.bandeev.it_courses.domain.auth.repositories.ExternalAuthNavigator

class ExternalAuthNavigatorImpl(val context: Context) : ExternalAuthNavigator {
    override fun openUrl(url: String, errorMessage: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
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
