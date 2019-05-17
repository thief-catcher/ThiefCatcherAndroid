package com.jbosak.mesproject.thiefcatcher

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlin.coroutines.coroutineContext


class ImageService(context: Context){
    private val BACKEND_URL = "http://192.168.0.107:5000"
    private val queue = Volley.newRequestQueue(context)!!

    fun fetchImages(images: MutableList<CapturedImage>, onUpdate: () -> Unit){

        val request2 = StringRequest("$BACKEND_URL/api/images", Response.Listener<String> { response ->

            val imgs = Gson().fromJson<Array<CapturedImage>>(response, Array<CapturedImage>::class.java)
            imgs
                .filter { images.contains(it).not() }
                .forEach {
                val request = ImageRequest( BACKEND_URL + "/api/images/" + it.name , Response.Listener<Bitmap> { response ->
                    it.img = response
                    images.add(0,it)
                    onUpdate()
                }, 800,1000, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                    Response.ErrorListener {error ->
                        Log.e(error.toString(), "ERROR")
                    })
                queue.add(request)
            }


        }, Response.ErrorListener {  })
        queue.add(request2)




    }

    fun captureImage(onUpdate: () -> Unit){
        val x = StringRequest(
            "$BACKEND_URL/api/images/capture",
            Response.Listener<String> {
                Log.e("captureImage", "onResponse")
                onUpdate() },
            Response.ErrorListener { Log.e(it.message, "ERROR") }
        )
        queue.add(x)
    }
}