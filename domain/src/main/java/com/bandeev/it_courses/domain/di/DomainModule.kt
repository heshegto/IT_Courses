package com.bandeev.it_courses.domain.di

import com.bandeev.it_courses.domain.auth.di.domainAuthModule
import com.bandeev.it_courses.domain.network.di.networkDi
import com.bandeev.it_courses.domain.storage.di.storageDi

val domainModule = arrayOf (
    domainAuthModule,
    networkDi,
    storageDi
)
