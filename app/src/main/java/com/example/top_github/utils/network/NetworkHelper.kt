package com.example.top_github.utils.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import javax.inject.Singleton

class NetworkHelper constructor (private val context: Context) {
    companion object {
        private const val TAG = "NetworkHelper"
    }

    fun isInternetConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return  activeNetwork?.isConnectedOrConnecting == true

//        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork = cm.activeNetworkInfo
//        return activeNetwork.isConnected
    }
}