package yskim.sample.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import yskim.sample.mvvmsampleapp.data.repositories.QuotesRepository
import yskim.sample.mvvmsampleapp.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(
    private val repository: QuotesRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuotesViewModel(repository) as T
        //return super.create(modelClass)
    }
}