package com.jbosak.mesproject.thiefcatcher

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Switch
import com.jbosak.mesproject.thiefcatcher.alarm.Alarm
import com.jbosak.mesproject.thiefcatcher.alarm.AlarmReceiver
import com.jbosak.mesproject.thiefcatcher.alarm.SwitchCompat
import com.jbosak.mesproject.thiefcatcher.retrofit.RetrofitBuilder
import com.jbosak.mesproject.thiefcatcher.ui.main.SectionsPagerAdapter
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        val imgService = ImageService(this)
        fab.setOnClickListener {
            imgService.captureImage{ sectionsPagerAdapter.notifyDataSetChanged() }
        }

        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        imgService.isAlarmOn {
            Log.e("FDSA", "FSFAS")
        }
        scheduleAlarm()


        val alarmSwitch = findViewById<Switch>(R.id.alarm_switch).run { this as Switch }
        val getAlarmStatus= RetrofitBuilder().create().isAlarmOn()
//        alarmSwitch.isChecked = true
//        val obsAlarm = RetrofitBuilder().create().isAlarmOn()
//        doAsync {
//
//            obsAlarm.subscribe {
//                Log.e("AlarmService", it.toString())
//                if (it.alarm){  alarmSwitch.isChecked = true}
//            }
//        }


        alarmSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            val call = RetrofitBuilder().create().postAlarm(Alarm(isChecked))
            doAsync {
                call.enqueue(object: Callback<Alarm> {
                    override fun onFailure(call: Call<Alarm>?, t: Throwable?) {
                        Log.v("retrofit", "call failed")
                    }

                    override fun onResponse(call: Call<Alarm>?, response: Response<Alarm>?) {
                            alarmSwitch.isChecked = response?.body()?.alarm!!
                    }

                })
            }


        }

    }

    private fun scheduleAlarm(){
        val intent = Intent(applicationContext, AlarmReceiver::class.java)
        val pIntent = PendingIntent.getBroadcast(
            this,
            AlarmReceiver.REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val firstMillis = System.currentTimeMillis()

        val interval = 1000L
        val alarm = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, interval, pIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item : MenuItem): Boolean{

        if(item.itemId == R.id.alarm_switch){
            Log.e("fdsf", (item as Switch).showText.toString())
//            (item as Switch).
        }
        return true
    }

}