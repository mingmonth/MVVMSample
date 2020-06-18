package yskim.sample.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import yskim.sample.mvvmsampleapp.data.repositories.UserRepository

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
        //authListener?.onSuccess()
        val loginResponse = UserRepository().userLogin(email!!, password!!)
        authListener?.onSuccess(loginResponse)
    }
}