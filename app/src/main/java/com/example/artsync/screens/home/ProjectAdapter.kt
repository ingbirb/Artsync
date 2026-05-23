package com.example.artsync.screens.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ProgressBar
import android.widget.TextView
import com.example.prefi.R
import com.example.artsync.data.Project

class ProjectAdapter(private val context: Context, private val projectList: ArrayList<Project>) : BaseAdapter() {

    override fun getCount(): Int = projectList.size

    override fun getItem(position: Int): Any = projectList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_project, parent, false)

        val project = projectList[position]

        val textTitle = view.findViewById<TextView>(R.id.textProjectTitle)
        val textDeadline = view.findViewById<TextView>(R.id.textProjectDeadline)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressProject)
        val textProgressPercent = view.findViewById<TextView>(R.id.textProgressPercent)
        val textCategory = view.findViewById<TextView>(R.id.textProjectCategory)

        textTitle.text = project.title
        textDeadline.text = "Deadline: ${project.deadline}"
        progressBar.progress = project.progress
        textProgressPercent.text = "${project.progress}%"
        textCategory.text = project.category

        return view
    }
}
