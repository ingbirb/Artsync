package com.example.artsync.screens.register

class RegisterContract {
    interface View {
        fun showSuccess()
        fun showEmptyField()
        fun showPasswordMismatch()
        fun showUsernameTaken()
        fun goToLogin()
    }

    interface Presenter {
        fun register(username: String, password: String, confirmPassword: String)
    }
}