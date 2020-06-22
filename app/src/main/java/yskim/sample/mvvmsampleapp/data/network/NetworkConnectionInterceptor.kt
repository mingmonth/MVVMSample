package yskim.sample.mvvmsampleapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response
import yskim.sample.mvvmsampleapp.util.NoInternetException

class NetworkConnectionInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvailable()) {
            throw NoInternetException("Make user you have an active data connection")
        }
        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable() : Boolean {

        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }

    }
}