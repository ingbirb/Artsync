package com.example.artsync.screens.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.artsync.app.Custom
import com.example.prefi.R
import com.example.artsync.screens.home.HomeActivity
import com.example.artsync.screens.register.RegisterActivity
import com.example.artsync.utility.getEditTextValue
import com.example.artsync.utility.getToast

class LoginActivity : Activity(), LoginContract.View {
    lateinit var Presenter: LoginPresenter

    var username = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Presenter = LoginPresenter(this, LoginModel(application as Custom))
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val textviewRegister = findViewById<TextView>(R.id.textviewRegister)

        buttonLogin.setOnClickListener{
           username = getEditTextValue(R.id.edittextUsername)
           password = getEditTextValue(R.id.edittextPassword)

            Presenter.login(username, password)
        }

        textviewRegister.setOnClickListener{

            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)

        }
    }

    override fun showSuccess() {
        getToast("Login successful!")
    }

    override fun showInvalidCredentials() {
        getToast("Credentials do not match!")
    }

    override fun showEmptyField() {
        getToast("Username and Password cannot be empty!")
    }

    override fun showHome() {
        val homeIntent = Intent(this, HomeActivity::class.java)
        startActivity(homeIntent)
        finish()
    }
}