package com.example.artsync.screens.register

import com.example.artsync.app.Custom

class RegisterModel(private val app: Custom) {
    fun isUsernameTaken(username: String): Boolean {
        return username.equals(app.defaultUsername, ignoreCase = true)
    }

    fun register(username: String, password: String) {
        app.defaultUsername = username
        app.defaultPassword = password
    }
}