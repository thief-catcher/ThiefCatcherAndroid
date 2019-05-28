package com.jbosak.mesproject.thiefcatcher.retrofit

import com.jbosak.mesproject.thiefcatcher.CapturedImage
import com.jbosak.mesproject.thiefcatcher.alarm.Alarm
import io.reactivex.Observable
import retrofit2.http.GET

interface ImageInfoService{

    @GET("/api/images")
    fun getImageNames(): Observable<ArrayList<CapturedImage>>

    @GET("/api/alarm")
    fun isAlarmOn(): Observable<Alarm>
}