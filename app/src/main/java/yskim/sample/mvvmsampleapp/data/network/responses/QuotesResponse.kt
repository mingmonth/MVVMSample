package yskim.sample.mvvmsampleapp.data.network.responses

import yskim.sample.mvvmsampleapp.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)