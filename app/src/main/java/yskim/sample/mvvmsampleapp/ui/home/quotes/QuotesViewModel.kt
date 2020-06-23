package yskim.sample.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import yskim.sample.mvvmsampleapp.data.repositories.QuotesRepository
import yskim.sample.mvvmsampleapp.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }

}