package com.example.prefi.data

data class Project(
    val title: String,
    val deadline: String,
    val progress: Int, // percentage 0-100
    val category: String
)
