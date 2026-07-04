package com.bandeev.it_courses.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bandeev.it_courses.all_courses.presentation.AllCoursesFragment
import com.bandeev.it_courses.favourite_courses.FavouriteCoursesFragment
import com.bandeev.it_courses.main.R
import com.google.android.material.navigation.NavigationBarView
import com.bandeev.it_courses.authentification.presentation.AuthentificationFragment

class MainActivity : AppCompatActivity() {

    enum class PossibleFragment (
        val tag: String,
        val createFragment: () -> Fragment
    ) {
        ALL_COURSES("ALL_COURSES", { AllCoursesFragment() }),
        FAVOURITES( "FAVOURITES", { FavouriteCoursesFragment() }),
        ACCOUNT("ACCOUNT", { AuthentificationFragment() })
    }

    private var activeFragment: PossibleFragment? = null
        // You can set new fragment just by setting new value for this variable
        set(value) {
            if (value == null) {
                field = null
                return
            }
            if (field == value)  {
                return
            }
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            // Hide old fragment
            manager.findFragmentByTag(field?.tag)?.let { transaction.hide(it) }
            // Add new fragment if it doesn't exist
            val nextFrag = manager.findFragmentByTag(value.tag) ?: value.createFragment().also {
                transaction.add(R.id.mainFrame, it, value.tag)
            }
            // Show new fragment, add it to stack
            transaction.show(nextFrag)
            transaction.setPrimaryNavigationFragment(nextFrag)

            // The End
            transaction.commit()
            field = value
        }

    override fun onCreate(savedInstancesBundle: Bundle?) {
        super.onCreate(savedInstancesBundle)
        setContentView(R.layout.main_layout)
        if (savedInstancesBundle == null ){ activeFragment = PossibleFragment.ALL_COURSES }

        val bottomNavMenu = findViewById<NavigationBarView>(R.id.bottomNavMenu)
        bottomNavMenu.setOnItemSelectedListener { item ->
            activeFragment = when (item.itemId) {
                R.id.BM_main ->  PossibleFragment.ALL_COURSES
                R.id.BM_favourites ->  PossibleFragment.FAVOURITES
                R.id.BM_account ->  PossibleFragment.ACCOUNT
                else -> null
            }
            activeFragment != null
        }
        if (savedInstancesBundle == null) {
            bottomNavMenu.selectedItemId = R.id.BM_main
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}