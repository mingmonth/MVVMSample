package yskim.sample.mvvmsampleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import yskim.sample.mvvmsampleapp.data.repositories.UserRepository
import yskim.sample.mvvmsampleapp.util.ApiException
import yskim.sample.mvvmsampleapp.util.Coroutines
import yskim.sample.mvvmsampleapp.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invalid email or password")
            return
        }

        // success
        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    println("saveUser:" + it.email)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }

//            val response = UserRepository().userLogin(email!!, password!!)
//            if(response.isSuccessful) {
//                var isError: Boolean = response.body()?.error!!
//                if(isError) {
//                    //authListener?.onFailure("Error Code: ${response.code()}")
//                    authListener?.onFailure(response.body()?.message!!)
//                } else {
//                    authListener?.onSuccess(response.body()?.user!!)
//                }
//            } else {
//                authListener?.onFailure("Error Code: ${response.code()}")
//            }
        }

        //authListener?.onSuccess()
//        val loginResponse = UserRepository().userLogin(email!!, password!!)
//        authListener?.onSuccess(loginResponse)
    }
}