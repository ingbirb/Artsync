package com.example.prefi.screens.inspiration

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.GridView

class InspirationAdapter(private val context: Context, private val imageIds: List<Int>) : BaseAdapter() {

    override fun getCount(): Int = imageIds.size

    override fun getItem(position: Int): Any = imageIds[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                500 // Height in pixels, consider using dp in a real app
            )
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }

        imageView.setImageResource(imageIds[position])
        return imageView
    }
}
