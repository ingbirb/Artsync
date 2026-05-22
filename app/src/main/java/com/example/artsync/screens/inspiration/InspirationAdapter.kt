package com.example.artsync.screens.inspiration

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.GridView

class InspirationAdapter(private val context: Context, private val items: MutableList<Any>) : BaseAdapter() {

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                500
            )
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }

        val item = items[position]
        when (item) {
            is Int -> imageView.setImageResource(item)
            is android.net.Uri -> imageView.setImageURI(item)
            is android.graphics.Bitmap -> imageView.setImageBitmap(item)
        }

        return imageView
    }
}
