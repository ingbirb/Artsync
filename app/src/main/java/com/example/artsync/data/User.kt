package com.example.artsync.data

data class User(
    var username: String = "",
    var password: String = "",
    var artStyle: String = "",
    var bio: String = "",
    var profileImageUri: String? = null
) {

}