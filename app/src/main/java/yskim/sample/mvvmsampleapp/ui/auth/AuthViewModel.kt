package yskim.sample.mvvmsampleapp.ui.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import yskim.sample.mvvmsampleapp.data.db.entities.User
import yskim.sample.mvvmsampleapp.data.repositories.UserRepository

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {
//    var username: String? = null
//    var email: String? = null
//    var password: String? = null
//    var passwordconfirm: String? = null
//
//    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    suspend fun userLogin(
        email: String,
        password: String
    ) = withContext(Dispatchers.IO) {
        repository.userLogin(email, password)
    }

    suspend fun userSignup(
        username: String,
        email: String,
        password: String
    ) = withContext(Dispatchers.IO) {
        repository.userSignup(username, email, password)
    }

    suspend fun saveLoggedInUser(user: User) = repository.saveUser(user)
//    fun onLoginButtonClick(view: View) {
//        authListener?.onStarted()
//        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
//            authListener?.onFailure("Invalid email or password")
//            return
//        }
//
//        // success
//        Coroutines.main {
//            try {
//                val authResponse = repository.userLogin(email!!, password!!)
//
//                authResponse.user?.let {
//                    authListener?.onSuccess(it)
//                    println("saveUser:" + it.email)
//                    repository.saveUser(it)
//                    return@main
//                }
//                authListener?.onFailure(authResponse.message!!)
//            } catch (e: ApiException) {
//                authListener?.onFailure(e.message!!)
//            } catch (e: NoInternetException) {
//                authListener?.onFailure(e.message!!)
//            }
//
////            val response = UserRepository().userLogin(email!!, password!!)
////            if(response.isSuccessful) {
////                var isError: Boolean = response.body()?.error!!
////                if(isError) {
////                    //authListener?.onFailure("Error Code: ${response.code()}")
////                    authListener?.onFailure(response.body()?.message!!)
////                } else {
////                    authListener?.onSuccess(response.body()?.user!!)
////                }
////            } else {
////                authListener?.onFailure("Error Code: ${response.code()}")
////            }
//        }
//
//        //authListener?.onSuccess()
////        val loginResponse = UserRepository().userLogin(email!!, password!!)
////        authListener?.onSuccess(loginResponse)
//    }
//
//    fun onLogin(view: View) {
//        Intent(view.context, LoginActivity::class.java).also{
//            view.context.startActivity(it)
//        }
//    }
//
//    fun onSignup(view: View) {
//        Intent(view.context, SignupActivity::class.java).also{
//           view.context.startActivity(it)
//        }
//    }
//
//    fun onSignupButtonClick(view: View) {
//        authListener?.onStarted()
//        if (username.isNullOrEmpty()) {
//            authListener?.onFailure("Name is required")
//            return
//        }
//
//        if (email.isNullOrEmpty()) {
//            authListener?.onFailure("Email is required")
//            return
//        }
//
//        if (password.isNullOrEmpty()) {
//            authListener?.onFailure("Please enter a password")
//            return
//        }
//
//        if (password != passwordconfirm) {
//            authListener?.onFailure("Password did not match")
//            return
//        }
//
//        // success
//        Coroutines.main {
//            try {
//                val authResponse = repository.userSignup(username!!, password!!, email!!)
//
//                authResponse.user?.let {
//                    authListener?.onSuccess(it)
//                    println("saveUser:" + it.email)
//                    repository.saveUser(it)
//                    return@main
//                }
//                authListener?.onFailure(authResponse.message!!)
//            } catch (e: ApiException) {
//                authListener?.onFailure(e.message!!)
//            } catch (e: NoInternetException) {
//                authListener?.onFailure(e.message!!)
//            }
//
//        }
//    }

}