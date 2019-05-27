package com.jbosak.mesproject.thiefcatcher.ui.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

import com.jbosak.mesproject.thiefcatcher.R


class LiveCameraFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_live_camera, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val web = view.findViewById<WebView>(R.id.web_live_camera)
        web.webViewClient = WebViewClient()

        web.loadUrl("http://192.168.0.107:5000/api/live")
    }
}

