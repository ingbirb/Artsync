package com.example.prefi.data

import java.io.Serializable

data class Project(
    val title: String,
    val deadline: String,
    var progress: Int, // percentage 0-100
    val category: String
) : Serializable
