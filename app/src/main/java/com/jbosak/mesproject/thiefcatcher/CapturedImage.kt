package com.jbosak.mesproject.thiefcatcher

import android.graphics.Bitmap

data class CapturedImage(val name: String, var img: Bitmap){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CapturedImage

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + img.hashCode()
        return result
    }
}