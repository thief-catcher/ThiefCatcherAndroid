package com.jbosak.mesproject.thiefcatcher.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.FrameLayout
import android.widget.GridView
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
        val images = arrayOf("aaa", "bbb", "cccc", "ddddddddddddddddddddddddd")
        viewAdapter = ImageAdapter(images)
        recyclerView = view.findViewById<RecyclerView>(R.id.gallery_recycler_view).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }



        return view
    }



}

