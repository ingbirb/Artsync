package com.example.prefi.screens.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.example.prefi.app.Custom
import com.example.prefi.R
import com.example.prefi.data.Project
import com.example.prefi.screens.login.LoginActivity

class HomeActivity : Activity(), HomeContract.View {

    private lateinit var dashboardPresenter: HomePresenter
    private lateinit var textViewWelcome: TextView
    
    // ArrayList and Adapter for ListView
    private lateinit var projectList: ArrayList<Project>
    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var listViewProjects: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val buttonBackToLogin = findViewById<Button>(R.id.buttonBackToLogin)
        val buttonAddProject = findViewById<Button>(R.id.buttonAddProject)

        textViewWelcome = findViewById<TextView>(R.id.textviewUser)
        listViewProjects = findViewById<ListView>(R.id.listViewProjects)

        dashboardPresenter = HomePresenter(this, HomeModel(application as Custom))
        dashboardPresenter.initializeUsername()

        // 1. Initialize ArrayList
        projectList = ArrayList()
        projectList.add(Project("Oil Painting", "2023-12-01", 30, "Fine Art"))
        projectList.add(Project("Logo Design", "2023-11-25", 80, "Freelance"))

        // 2. Initialize Custom Adapter
        projectAdapter = ProjectAdapter(this, projectList)
        listViewProjects.adapter = projectAdapter

        // 3. Custom ListView Click Listener
        listViewProjects.setOnItemClickListener { _, _, position, _ ->
            val selectedProject = projectList[position]
            Toast.makeText(this, "Opening: ${selectedProject.title}", Toast.LENGTH_SHORT).show()
        }

        // 4. Custom ListView Long Click Listener (Remove Item)
        listViewProjects.setOnItemLongClickListener { _, _, position, _ ->
            val removedProject = projectList[position]
            projectList.removeAt(position)
            projectAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Removed: ${removedProject.title}", Toast.LENGTH_SHORT).show()
            true
        }

        // 5. Add Item Example
        buttonAddProject.setOnClickListener {
            val newProject = Project("New Sketch", "2023-12-15", 0, "Hobby")
            projectList.add(newProject)
            projectAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Added New Project", Toast.LENGTH_SHORT).show()
        }

        buttonBackToLogin.setOnClickListener{
            val backToLoginIntent = Intent(this, LoginActivity::class.java)
            startActivity(backToLoginIntent)
        }
    }

    override fun displayUsername(message: String) {
        textViewWelcome.setText(message)
    }
}