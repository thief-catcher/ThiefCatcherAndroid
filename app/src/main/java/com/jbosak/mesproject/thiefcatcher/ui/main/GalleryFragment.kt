package com.jbosak.mesproject.thiefcatcher.ui.main

import android.database.DataSetObserver
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.jbosak.mesproject.thiefcatcher.CapturedImage
import com.jbosak.mesproject.thiefcatcher.ImageService
import com.jbosak.mesproject.thiefcatcher.R

class GalleryFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<ImageAdapter.ImageViewHolder>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.gallery_fragment, container, false)
        viewManager = LinearLayoutManager(view.context)

        val imageService = ImageService(view.context)
        var images = mutableListOf<CapturedImage>()
        viewAdapter = ImageAdapter(images)
        imageService.fetchImages(images) { viewAdapter.notifyDataSetChanged() }

        recyclerView = view.findViewById<RecyclerView>(R.id.gallery_recycler_view).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }



        return view
    }



}

