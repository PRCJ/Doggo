package com.clevertap.doggo.utils
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import kotlin.collections.HashMap
class Utils(val context: Context) {
    fun verifyAvailableNetwork(): Boolean {
        val connectivityManager =
                context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (!(networkInfo != null && networkInfo.isConnected)) {
            Toast.makeText(
                    context!!,
                    "Network connection unavailable",
                    Toast.LENGTH_SHORT
            ).show()
        }
        return networkInfo != null && networkInfo.isConnected
    }
    fun logD(tag: String?, msg: String?) {
            Log.d(tag, msg!!)

    }
}