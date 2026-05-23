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
import android.text.Editable
import android.text.TextWatcher
import com.example.prefi.R
import com.example.artsync.app.Custom
import com.example.artsync.data.Project

class ProjectActivity : AppCompatActivity(), ProjectContract.View {

    private lateinit var presenter: ProjectPresenter
    private lateinit var editTextTitle: EditText
    private lateinit var editTextCategory: EditText
    private lateinit var textViewProgress: TextView
    private lateinit var textViewProgressLabel: TextView
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
        editTextCategory = findViewById(R.id.editTextDetailCategory)
        textViewProgress = findViewById(R.id.textViewProgressValue)
        textViewProgressLabel = findViewById(R.id.textViewProgressLabel)
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

        editTextTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textViewProgressLabel.text = if (s.isNullOrEmpty()) "Completion Progress" else "$s - Completion Progress"
            }
            override fun afterTextChanged(s: Editable?) {}
        })

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
                
                // Update title and category from EditText
                val newTitle = editTextTitle.text.toString().trim()
                if (newTitle.isNotEmpty()) {
                    it.title = newTitle
                }
                
                val newCategory = editTextCategory.text.toString().trim()
                if (newCategory.isNotEmpty()) {
                    it.category = newCategory
                }
                
                selectedImageUri?.let { uri ->
                    it.imageUri = uri.toString()
                }
                presenter.saveProject(it)
            }
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }

    override fun showProjectDetails(project: Project) {
        editTextTitle.setText(project.title)
        textViewProgressLabel.text = "${project.title} - Completion Progress"
        editTextCategory.setText(project.category)
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

        val resultIntent = Intent()
        resultIntent.putExtra("updated_project", currentProject)
        setResult(RESULT_OK, resultIntent)

        finish()
    }
}
