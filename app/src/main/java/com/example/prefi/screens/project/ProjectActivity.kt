package com.example.prefi.screens.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.example.prefi.R
import com.example.prefi.app.Custom
import com.example.prefi.data.Project

class ProjectActivity : AppCompatActivity(), ProjectContract.View {

    private lateinit var presenter: ProjectPresenter
    private lateinit var textViewTitle: TextView
    private lateinit var textViewCategory: TextView
    private lateinit var textViewProgress: TextView
    private lateinit var seekBarProgress: SeekBar
    private var currentProject: Project? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)

        textViewTitle = findViewById(R.id.textViewDetailTitle)
        textViewCategory = findViewById(R.id.textViewDetailCategory)
        textViewProgress = findViewById(R.id.textViewProgressValue)
        seekBarProgress = findViewById(R.id.seekBarProgress)
        val buttonSave = findViewById<Button>(R.id.buttonSaveProgress)
        val buttonBack = findViewById<Button>(R.id.buttonBackHome)

        presenter = ProjectPresenter(this, ProjectModel(application as Custom))

        currentProject = intent.getSerializableExtra("project") as? Project
        presenter.loadProject(currentProject)

        seekBarProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                presenter.onProgressChanged(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        buttonSave.setOnClickListener {
            currentProject?.let {
                presenter.saveProgress(it, seekBarProgress.progress)
                val resultIntent = Intent()
                resultIntent.putExtra("updated_project", it)
                setResult(RESULT_OK, resultIntent)
            }
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }

    override fun showProjectDetails(project: Project) {
        textViewTitle.text = project.title
        textViewCategory.text = "Category: ${project.category}"
        textViewProgress.text = "${project.progress}%"
        seekBarProgress.progress = project.progress
    }

    override fun updateProgressText(progress: Int) {
        textViewProgress.text = "$progress%"
    }

    override fun onProgressUpdated(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }
}
