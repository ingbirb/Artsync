package com.example.prefi.screens.inspiration

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import com.example.prefi.R

class InspirationActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspiration)

        val gridView = findViewById<GridView>(R.id.gridInspiration)
        val buttonUpload = findViewById<Button>(R.id.buttonUpload)
        val buttonBack = findViewById<Button>(R.id.buttonBackHome)

        val images = listOf(
            R.drawable.ref,
            R.drawable.ref1,
            R.drawable.ref2,
            R.drawable.red3, // User likely meant ref3, but file is named red3.jpg
            R.drawable.ref4,
            R.drawable.ref5,
            R.drawable.ref6,
            R.drawable.ref7
        )

        val adapter = InspirationAdapter(this, images)
        gridView.adapter = adapter

        buttonUpload.setOnClickListener {
            Toast.makeText(this, "Upload feature coming soon!", Toast.LENGTH_SHORT).show()
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }
}