package com.example.prefi.app

import android.app.Application
import com.example.prefi.data.User

class Custom: Application(){
    var defaultUsername = "test"
    var defaultPassword = "123"

    var loginUser = User()

}