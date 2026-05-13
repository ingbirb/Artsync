package com.example.prefi.screens.home

class HomeContract {
    interface View {
        fun displayUsername(message: String)
    }

    interface Presenter{
        fun initializeUsername()
    }

}