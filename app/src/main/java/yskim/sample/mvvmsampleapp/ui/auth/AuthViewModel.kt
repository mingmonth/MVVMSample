package yskim.sample.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import yskim.sample.mvvmsampleapp.data.repositories.UserRepository
import yskim.sample.mvvmsampleapp.util.Coroutines

class AuthViewModel : ViewModel() {
    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        // success
        Coroutines.main {
            val response = UserRepository().userLogin(email!!, password!!)
            if(response.isSuccessful) {
                var isError: Boolean = response.body()?.error!!
                if(isError) {
                    authListener?.onFailure("Error Code: ${response.code()}")
                } else {
                    authListener?.onSuccess(response.body()?.user!!)
                }
            } else {
                authListener?.onFailure("Error Code: ${response.code()}")
            }
        }

        //authListener?.onSuccess()
//        val loginResponse = UserRepository().userLogin(email!!, password!!)
//        authListener?.onSuccess(loginResponse)
    }
}