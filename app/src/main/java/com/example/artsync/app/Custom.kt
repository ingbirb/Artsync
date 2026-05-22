package com.example.artsync.app

import android.app.Application
import com.example.artsync.data.User

class Custom: Application(){
    var defaultUsername = "test"
    var defaultPassword = "123"

    var loginUser = User()

}