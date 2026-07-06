package com.bandeev.it_courses.authentification.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.bandeev.it_courses.authentification.R
import com.bandeev.it_courses.domain.auth.models.LogInViaEmailData
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AuthentificationFragment: Fragment(R.layout.authentification_layout) {
    private val viewModel: AuthViewModel by activityViewModel()
    private val btnLogin by lazy { view?.findViewById<Button>(R.id.button_logIn) }
    private val etEmail by lazy { view?.findViewById<EditText>(R.id.edit_email) }
    private val etPassword by lazy { view?.findViewById<EditText>(R.id.edit_password) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnVK = view.findViewById<ImageButton>(R.id.imButton_vk)
        val btnOK = view.findViewById<ImageButton>(R.id.imButton_ok)
        val btnSignUp = view.findViewById<TextView>(R.id.tv_signUp)
        val btnForgotPassword = view.findViewById<TextView>(R.id.tv_forgotPass)

        btnLogin?.setOnClickListener {
            viewModel.clickLogIn(
                getString(R.string.wrong_log_in_data),
                getString(R.string.invalid_log_in_data)
            )
        }
        btnVK.setOnClickListener { viewModel.clickAuthVK(getString(R.string.browser_error)) }
        btnOK.setOnClickListener { viewModel.clickAuthOK(getString(R.string.browser_error)) }
        btnSignUp.setOnClickListener { viewModel.clickSignUp() }
        btnForgotPassword.setOnClickListener { viewModel.clickForgotPassword() }

        recoverUIState()
        updateUIState()
        etEmail?.doAfterTextChanged { updateUIState() }
        etPassword?.doAfterTextChanged { updateUIState() }
    }

    private fun recoverUIState(){
        viewModel.logInActivityDataState?.let {
            etEmail?.setText(it.email)
            etPassword?.setText(it.password)
        }
    }

    private fun updateUIState() {
        val email = etEmail?.text.toString().trim()
        val password = etPassword?.text.toString()
        viewModel.logInActivityDataState = LogInViaEmailData(email, password)
        btnLogin?.isEnabled = viewModel.logInActivityDataState!!.isValid()
    }
}