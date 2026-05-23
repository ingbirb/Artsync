package com.example.artsync.screens.project

import com.example.artsync.data.Project

class ProjectPresenter(
    private val view: ProjectContract.View,
    private val model: ProjectModel
) : ProjectContract.Presenter {

    override fun loadProject(project: Project?) {
        if (project != null) {
            view.showProjectDetails(project)
        }
    }

    override fun onProgressChanged(progress: Int) {
        view.updateProgressText(progress)
    }

    override fun saveProject(project: Project) {
        model.updateProject(project)
        view.onProgressUpdated("Project '${project.title}' updated successfully!")
    }
}
