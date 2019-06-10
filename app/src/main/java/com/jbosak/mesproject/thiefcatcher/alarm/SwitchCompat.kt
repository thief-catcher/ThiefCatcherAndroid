package com.jbosak.mesproject.thiefcatcher.alarm

import android.content.Context
import android.widget.CompoundButton
import android.widget.Switch


class SwitchCompat(context:Context) : Switch(context) {
    private var mIgnoreCheckedChange = false


    override fun setOnCheckedChangeListener(listener: CompoundButton.OnCheckedChangeListener?) {
        super.setOnCheckedChangeListener(OnCheckedChangeListener { buttonView, isChecked ->
            if (mIgnoreCheckedChange) {
                return@OnCheckedChangeListener
            }
            listener!!.onCheckedChanged(buttonView, isChecked)
        })
    }

    fun setChecked(checked: Boolean, notify: Boolean) {
        mIgnoreCheckedChange = !notify
        isChecked = checked
        mIgnoreCheckedChange = false
    }

}