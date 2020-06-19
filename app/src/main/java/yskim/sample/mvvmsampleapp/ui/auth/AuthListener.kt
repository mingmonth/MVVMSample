package yskim.sample.mvvmsampleapp.ui.auth

import androidx.lifecycle.LiveData
import yskim.sample.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun onStarted()
    //fun onSuccess(loginResponse: LiveData<String>)
    fun onSuccess(user: User)
    fun onFailure(message: String)
}