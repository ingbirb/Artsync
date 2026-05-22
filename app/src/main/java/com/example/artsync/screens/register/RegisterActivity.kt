package com.example.artsync.screens.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.example.artsync.app.Custom
import com.example.prefi.R
import com.example.artsync.screens.login.LoginActivity
import com.example.artsync.utility.getEditTextValue
import com.example.artsync.utility.getToast

class RegisterActivity : Activity(), RegisterContract.View {
    private lateinit var registerPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerPresenter = RegisterPresenter(this, RegisterModel(application as Custom))

        val buttonRegister = findViewById<Button>(R.id.btnSubmit)


        buttonRegister.setOnClickListener {
            val username = getEditTextValue(R.id.edittextRegUsername)
            val password = getEditTextValue(R.id.edittextRegPassword)
            val confirmPassword = getEditTextValue(R.id.edittextRegRePassword)

            registerPresenter.register(username, password, confirmPassword)
        }
    }

    override fun showSuccess() {
        getToast("Registration successful!")
    }

    override fun showEmptyField() {
        getToast("All fields cannot be empty!")
    }

    override fun showPasswordMismatch() {
        getToast("Passwords do not match!")
    }

    override fun showUsernameTaken() {
        getToast("Username is already taken!")
    }

    override fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}