package com.jbosak.mesproject.thiefcatcher.ui.main

import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jbosak.mesproject.thiefcatcher.R

class ImageAdapter(private val images: MutableList<Bitmap>): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cell = layoutInflater.inflate(R.layout.gallery_card_view, parent, false)

        return ImageViewHolder(cell)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, p1: Int) {

        holder.img.setImageBitmap(images[p1])
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val date: TextView = itemView.findViewById(R.id.gallery_date_image)
        val img: ImageView = itemView.findViewById(R.id.gallery_image)
    }



}