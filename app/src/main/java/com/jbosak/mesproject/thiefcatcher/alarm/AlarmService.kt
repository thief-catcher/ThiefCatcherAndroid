package com.jbosak.mesproject.thiefcatcher.alarm

import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.jbosak.mesproject.thiefcatcher.MainActivity
import com.jbosak.mesproject.thiefcatcher.retrofit.RetrofitBuilder

class AlarmService: IntentService("AlarmService"){
    override fun onHandleIntent(intent: Intent?) {
        Log.e("AlarmService", "Service Running")

        val x = RetrofitBuilder().create().isAlarmOn()
        x.subscribe {
            Log.e("AlarmService", it.toString())
            if (it.alarm){ addNotification() }
        }

    }

    private fun addNotification() {
        val builder = NotificationCompat.Builder(this)
            .setSmallIcon(android.R.drawable.btn_dialog)
            .setContentTitle("Something happened in your house")
            .setContentText("Check out the camera!")

        val notificationIntent = Intent(this, MainActivity::class.java)
        val contentIntent = PendingIntent.getActivity(
            this, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(contentIntent)


        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder.build())
    }

}