package com.example.artsync.screens.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.prefi.R
import com.example.artsync.app.Custom

class ProfileActivity : ComponentActivity() {

    private lateinit var editName: EditText
    private lateinit var editBio: EditText
    private lateinit var editStyle: EditText
    private lateinit var buttonEdit: Button
    private lateinit var buttonSave: Button
    private lateinit var imageProfile: ImageView
    private lateinit var app: Custom
    
    private var selectedImageUri: Uri? = null

    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            imageProfile.setImageURI(it)
            // Save immediately or wait for Save button? 
            // Usually profile images are saved immediately or when 'Save' is clicked.
            // Let's update it in the user object when Save is clicked for consistency.
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        app = application as Custom
        editName = findViewById(R.id.editProfileName)
        editBio = findViewById(R.id.editBio)
        editStyle = findViewById(R.id.editStylePrefs)
        buttonEdit = findViewById(R.id.buttonEditProfile)
        buttonSave = findViewById(R.id.buttonSaveProfile)
        imageProfile = findViewById(R.id.imageProfile)
        val buttonBack = findViewById<Button>(R.id.buttonBackHome)

        editName.setText(app.loginUser.username)
        editBio.setText(app.loginUser.bio)
        editStyle.setText(app.loginUser.artStyle)
        
        app.loginUser.profileImageUri?.let {
            imageProfile.setImageURI(Uri.parse(it))
        }

        buttonEdit.setOnClickListener {
            toggleEditMode(true)
        }

        buttonSave.setOnClickListener {
            saveProfile()
        }

        buttonBack.setOnClickListener {
            finish()
        }
        
        imageProfile.setOnClickListener {
            if (buttonSave.visibility == View.VISIBLE) { // Only allow changing in edit mode
                imagePickerLauncher.launch("image/*")
            } else {
                Toast.makeText(this, "Click 'Edit' to change profile picture", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun toggleEditMode(isEditing: Boolean) {
        editName.isEnabled = isEditing
        editBio.isEnabled = isEditing
        editStyle.isEnabled = isEditing
        
        if (isEditing) {
            editName.requestFocus()
            buttonEdit.visibility = View.GONE
            buttonSave.visibility = View.VISIBLE
            // Add background to show it's editable
            editName.setBackgroundResource(android.R.drawable.edit_text)
            editBio.setBackgroundResource(android.R.drawable.edit_text)
            editStyle.setBackgroundResource(android.R.drawable.edit_text)
        } else {
            buttonEdit.visibility = View.VISIBLE
            buttonSave.visibility = View.GONE
            editName.background = null
            editBio.background = null
            editStyle.background = null
        }
    }

    private fun saveProfile() {
        val newName = editName.text.toString()
        val newBio = editBio.text.toString()
        val newStyle = editStyle.text.toString()

        app.loginUser.username = newName
        app.loginUser.bio = newBio
        app.loginUser.artStyle = newStyle
        selectedImageUri?.let {
            app.loginUser.profileImageUri = it.toString()
        }

        Toast.makeText(this, "Profile Updated!", Toast.LENGTH_SHORT).show()
        toggleEditMode(false)
    }
}