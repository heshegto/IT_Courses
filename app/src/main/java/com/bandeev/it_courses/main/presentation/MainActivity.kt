package com.bandeev.it_courses.main.presentation

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope

import com.bandeev.it_courses.authentification.presentation.AuthViewModel
import com.bandeev.it_courses.main.R
import com.bandeev.it_courses.main.navigation.PossibleFragment

import com.google.android.material.navigation.NavigationBarView
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: AuthViewModel by viewModel()

    private var activeFragment: PossibleFragment? = null
        // You can set new fragment just by setting new value for this variable
        set(value) {
            if (value == null) {
                field = null
                return
            }
            if (field == value) {
                return
            }
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            currentFocus?.let { view -> imm.hideSoftInputFromWindow(view.windowToken, 0) }

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
        val bottomNavMenu = findViewById<NavigationBarView>(R.id.bottomNavMenu)

        if (savedInstancesBundle == null) {
            bottomNavMenu.visibility = NavigationBarView.GONE
            activeFragment = PossibleFragment.AUTH
        }

        bottomNavMenu.setOnItemSelectedListener { item ->
            activeFragment = when (item.itemId) {
                R.id.BM_main -> PossibleFragment.ALL_COURSES
                R.id.BM_favourites -> PossibleFragment.FAVOURITES
                R.id.BM_account -> PossibleFragment.ACCOUNT
                else -> null
            }
            activeFragment != null
        }

        lifecycleScope.launch {
            viewModel.logInResult.collect {
                if (it.isLoggedIn) {
                    bottomNavMenu.visibility = NavigationBarView.VISIBLE
                    activeFragment = PossibleFragment.ALL_COURSES
                    bottomNavMenu.selectedItemId = R.id.BM_main
                }
            }
        }
    }
}