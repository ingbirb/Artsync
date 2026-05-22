package com.example.artsync.screens.home

import com.example.artsync.app.Custom

class HomeModel(private val app: Custom) {
    fun getUsername(): String {
        return app.loginUser.username
    }
}