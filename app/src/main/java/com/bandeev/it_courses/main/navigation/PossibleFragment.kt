package com.bandeev.it_courses.main.navigation

import androidx.fragment.app.Fragment
import com.bandeev.it_courses.account_management.presentation.AccountManagementFragment
import com.bandeev.it_courses.all_courses.presentation.AllCoursesFragment
import com.bandeev.it_courses.authentification.presentation.AuthentificationFragment
import com.bandeev.it_courses.favourite_courses.presentation.FavouriteCoursesFragment

enum class PossibleFragment (
    val tag: String,
    val createFragment: () -> Fragment
) {
    ALL_COURSES("ALL_COURSES", { AllCoursesFragment() }),
    FAVOURITES( "FAVOURITES", { FavouriteCoursesFragment() }),
    ACCOUNT("ACCOUNT", { AccountManagementFragment() }),
    AUTH("AUTH", { AuthentificationFragment() })
}