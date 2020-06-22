package yskim.sample.mvvmsampleapp.ui.home.profile

import androidx.lifecycle.ViewModel
import yskim.sample.mvvmsampleapp.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()



}