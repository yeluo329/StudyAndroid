package com.example.wanandroid.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

inline fun <reified T : Activity> Context.startActivity(
    data: Bundle? = null,
    requestCode: Int? = 0,
) {
    val intent = Intent(this, T::class.java)
    data?.let {
        intent.putExtras(data)
    }
    if (requestCode != 0) {
        requestCode?.let {
            (this as Activity).startActivityForResult(intent, it)
        }
    } else {
        this.startActivity(intent)
    }

}