package com.example.myapplication.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkReceiver(val mListener:(Boolean)->Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!=null&&intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            mListener.invoke(NetWorkUtils.isNetworkReachable())
        }
    }


}