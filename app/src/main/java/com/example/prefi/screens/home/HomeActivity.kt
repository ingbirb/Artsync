package com.example.prefi.screens.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.prefi.app.Custom
import com.example.prefi.R
import com.example.prefi.screens.login.LoginActivity

class HomeActivity : Activity(), HomeContract.View {

    private lateinit var dashboardPresenter: HomePresenter
    private lateinit var textViewWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val buttonBackToLogin = findViewById<Button>(R.id.buttonBackToLogin)

        textViewWelcome = findViewById<TextView>(R.id.textviewUser)

        dashboardPresenter = HomePresenter(this, HomeModel(application as Custom))
        dashboardPresenter.initializeUsername()

        buttonBackToLogin.setOnClickListener{
            val backToLoginIntent = Intent(this, LoginActivity::class.java)
            startActivity(backToLoginIntent)
        }
    }

    override fun displayUsername(message: String) {
        textViewWelcome.setText(message)
    }
}