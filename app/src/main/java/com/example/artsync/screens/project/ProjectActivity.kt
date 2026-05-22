package com.example.artsync.screens.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.ImageView
import com.example.prefi.R
import com.example.artsync.app.Custom
import com.example.artsync.data.Project

class ProjectActivity : AppCompatActivity(), ProjectContract.View {

    private lateinit var presenter: ProjectPresenter
    private lateinit var editTextTitle: EditText
    private lateinit var textViewCategory: TextView
    private lateinit var textViewProgress: TextView
    private lateinit var seekBarProgress: SeekBar
    private lateinit var imageViewProject: ImageView
    private var currentProject: Project? = null
    private var selectedImageUri: Uri? = null

    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            imageViewProject.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_detail)

        editTextTitle = findViewById(R.id.editTextDetailTitle)
        textViewCategory = findViewById(R.id.textViewDetailCategory)
        textViewProgress = findViewById(R.id.textViewProgressValue)
        seekBarProgress = findViewById(R.id.seekBarProgress)
        imageViewProject = findViewById(R.id.imageViewProject)
        val buttonSave = findViewById<Button>(R.id.buttonSaveProgress)
        val buttonBack = findViewById<Button>(R.id.buttonBackHome)
        val buttonSelectImage = findViewById<Button>(R.id.buttonSelectImage)

        presenter = ProjectPresenter(this, ProjectModel(application as Custom))

        currentProject = intent.getSerializableExtra("project") as? Project
        presenter.loadProject(currentProject)

        buttonSelectImage.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }

        seekBarProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                presenter.onProgressChanged(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        buttonSave.setOnClickListener {
            currentProject?.let {
                it.progress = seekBarProgress.progress // Update local object
                
                // Update title from EditText
                val newTitle = editTextTitle.text.toString().trim()
                if (newTitle.isNotEmpty()) {
                    it.title = newTitle
                }
                
                selectedImageUri?.let { uri ->
                    it.imageUri = uri.toString()
                }
                presenter.saveProgress(it, it.progress)
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
        editTextTitle.setText(project.title)
        textViewCategory.text = "Category: ${project.category}"
        textViewProgress.text = "${project.progress}%"
        seekBarProgress.progress = project.progress
        project.imageUri?.let {
            imageViewProject.setImageURI(Uri.parse(it))
        }
    }

    override fun updateProgressText(progress: Int) {
        textViewProgress.text = "$progress%"
    }

    override fun onProgressUpdated(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }
}
