package com.example.prefi.screens.project

import com.example.prefi.data.Project

class ProjectContract {
    interface View {
        fun showProjectDetails(project: Project)
        fun updateProgressText(progress: Int)
        fun onProgressUpdated(message: String)
    }

    interface Presenter {
        fun loadProject(project: Project?)
        fun onProgressChanged(progress: Int)
        fun saveProgress(project: Project, newProgress: Int)
    }
}
