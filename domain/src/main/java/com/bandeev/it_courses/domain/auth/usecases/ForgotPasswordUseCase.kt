package com.bandeev.it_courses.domain.auth.usecases

import com.bandeev.it_courses.domain.auth.repositories.MyToast

class ForgotPasswordUseCase(val myToast: MyToast) {
    fun execute(){
        myToast.doToast("Кнопка Забыл пароль нажата")
    }
}