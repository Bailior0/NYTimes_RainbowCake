package hu.autsoft.nytimes_gyulai_rainbowcake.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object ConnectivityChecker {
    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities)
        if(actNw != null) {
            if(actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                return true
            if(actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                return true
            if(actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                return true
        }

        return false
    }
}