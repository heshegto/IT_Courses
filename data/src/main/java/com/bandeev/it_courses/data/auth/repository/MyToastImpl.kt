package com.bandeev.it_courses.data.auth.repository

import android.content.Context
import android.widget.Toast
import com.bandeev.it_courses.domain.auth.repositories.MyToast

class MyToastImpl(val context: Context) : MyToast {
    override fun doToast(text: String) {
        Toast.makeText(
            context,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}