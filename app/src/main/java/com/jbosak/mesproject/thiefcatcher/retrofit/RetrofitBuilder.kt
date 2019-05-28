package com.jbosak.mesproject.thiefcatcher.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder{
    companion object {
        const val baseUrl = "http://192.168.0.114:5000/"
    }
    fun create(): ImageInfoService {
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

            return retrofit.create(ImageInfoService::class.java)
        }

}