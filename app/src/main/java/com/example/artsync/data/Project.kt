package com.example.artsync.data

import java.io.Serializable

data class Project(
    var title: String,
    var deadline: String,
    var progress: Int, // percentage 0-100
    var category: String,
    var imageUri: String? = null
) : Serializable
