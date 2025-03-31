package com.example.test_firebase_w5.function

import android.content.Context
import android.net.ConnectivityManager

fun isOnline(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
    return connectivityManager.activeNetworkInfo?.isConnected == true
}