package com.example.prefi.screens.profile

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.prefi.R
import com.example.prefi.app.Custom

class ProfileActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val app = application as Custom
        val textProfileName = findViewById<TextView>(R.id.textProfileName)
        val textStylePrefs = findViewById<TextView>(R.id.textStylePrefs)
        val buttonBack = findViewById<Button>(R.id.buttonBackHome)

        textProfileName.text = app.loginUser.username
        if (app.loginUser.artStyle.isNotEmpty()) {
            textStylePrefs.text = app.loginUser.artStyle
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }
}