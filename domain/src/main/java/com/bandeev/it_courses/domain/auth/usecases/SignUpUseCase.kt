package com.bandeev.it_courses.domain.auth.usecases

import com.bandeev.it_courses.domain.auth.repositories.MyToast

class SignUpUseCase(val myToast: MyToast) {
    fun execute(){
        myToast.doToast("Кнопка регистрации нажата")
    }
}