package com.kjbriyan.katalogmovie.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

class Helper {

    fun moveActivity(context: Context?, tujuan: Class<*>) {
        val i = Intent(context, tujuan)
        context?.startActivity(i)
    }

    fun debuger(text: String) {
        println("Debug : $text")
    }


}