package com.example.prefi.screens.inspiration

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.prefi.R

class InspirationActivity : AppCompatActivity() {

    private lateinit var adapter: InspirationAdapter
    private val imageList = mutableListOf<Any>()

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageList.add(it)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Image added from gallery", Toast.LENGTH_SHORT).show()
        }
    }

    private val takePhotoLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
        bitmap?.let {
            imageList.add(it)
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Photo added from camera", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspiration)

        val gridView = findViewById<GridView>(R.id.gridInspiration)
        val buttonUpload = findViewById<Button>(R.id.buttonUpload)
        val buttonBack = findViewById<Button>(R.id.buttonBackHome)

        // Initial sample data
        imageList.addAll(listOf(
            R.drawable.ref,
            R.drawable.ref1,
            R.drawable.ref2,
            R.drawable.red3,
            R.drawable.ref4,
            R.drawable.ref5,
            R.drawable.ref6,
            R.drawable.ref7
        ))

        adapter = InspirationAdapter(this, imageList)
        gridView.adapter = adapter

        buttonUpload.setOnClickListener {
            showImagePickerOptions()
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun showImagePickerOptions() {
        val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Sketch")
        builder.setItems(options) { dialog, item ->
            when (options[item]) {
                "Take Photo" -> takePhotoLauncher.launch(null)
                "Choose from Gallery" -> pickImageLauncher.launch("image/*")
                "Cancel" -> dialog.dismiss()
            }
        }
        builder.show()
    }
}
