package com.jbosak.mesproject.thiefcatcher

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley

class ImageService(context: Context){
    private val queue = Volley.newRequestQueue(context)!!

    fun fetchImages(images: MutableList<Bitmap>, onUpdate: () -> Unit){

        val request = ImageRequest( "http://192.168.0.107:8000/my_photo-10.jpg", Response.Listener<Bitmap> { response ->
            images.add(response)
            onUpdate()
        }, 800,1000, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
            Response.ErrorListener {
                Log.e(it.toString(), "ERROR")
            })
        queue.add(request)

    }
}