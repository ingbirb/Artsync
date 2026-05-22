package com.example.artsync.screens.project

import com.example.artsync.data.Project

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
