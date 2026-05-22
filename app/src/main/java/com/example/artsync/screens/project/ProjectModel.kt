package com.example.artsync.screens.project

import com.example.artsync.app.Custom
import com.example.artsync.data.Project

class ProjectModel(private val app: Custom) {
    // In a real app, this would update a database or API
    fun updateProject(project: Project) {
        // Here you would normally save to a database or server
        // For now, we are modifying the object directly which is reflected in HomeActivity
    }

    fun updateProjectProgress(project: Project, newProgress: Int) {}
}
