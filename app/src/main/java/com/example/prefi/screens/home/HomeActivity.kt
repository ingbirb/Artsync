package com.example.prefi.screens.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.prefi.app.Custom
import com.example.prefi.R
import com.example.prefi.data.Project
import com.example.prefi.screens.login.LoginActivity
import com.example.prefi.screens.project.ProjectActivity

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private lateinit var dashboardPresenter: HomePresenter
    private lateinit var textViewWelcome: TextView
    
    private lateinit var projectList: ArrayList<Project>
    private lateinit var projectAdapter: ProjectAdapter
    private lateinit var listViewProjects: ListView
    private var selectedPosition: Int = -1

    private val detailLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val updatedProject = result.data?.getSerializableExtra("updated_project") as? Project
            if (updatedProject != null && selectedPosition != -1) {
                projectList[selectedPosition] = updatedProject
                projectAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Project updated!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val buttonAddProject = findViewById<Button>(R.id.buttonAddProject)
        val buttonProfile = findViewById<Button>(R.id.buttonProfile)
        val buttonInspiration = findViewById<Button>(R.id.buttonInspiration)
        val buttonLogout = findViewById<Button>(R.id.buttonLogout)

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
            selectedPosition = position
            val selectedProject = projectList[position]
            val intent = Intent(this, ProjectActivity::class.java)
            intent.putExtra("project", selectedProject)
            detailLauncher.launch(intent)
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

        buttonProfile.setOnClickListener {
            startActivity(Intent(this, com.example.prefi.screens.profile.ProfileActivity::class.java))
        }

        buttonInspiration.setOnClickListener {
            startActivity(Intent(this, com.example.prefi.screens.inspiration.InspirationActivity::class.java))
        }

        buttonLogout.setOnClickListener {
            (application as Custom).loginUser = com.example.prefi.data.User() // Clear session
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    override fun displayUsername(message: String) {
        textViewWelcome.setText(message)
    }
}
