package com.example.prefi.screens.home

import com.example.prefi.app.Custom

class HomeModel(private val app: Custom) {
    fun getUsername(): String {
        return app.loginUser.username
    }
}