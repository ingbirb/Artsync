package com.example.prefi.screens.project

import com.example.prefi.app.Custom
import com.example.prefi.data.Project

class ProjectModel(private val app: Custom) {
    // In a real app, this would update a database or API
    fun updateProjectProgress(project: Project, newProgress: Int) {
        project.progress = newProgress
    }
}
