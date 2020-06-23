package yskim.sample.mvvmsampleapp.ui.home.quotes

import com.xwray.groupie.databinding.BindableItem
import yskim.sample.mvvmsampleapp.R
import yskim.sample.mvvmsampleapp.data.db.entities.Quote
import yskim.sample.mvvmsampleapp.databinding.ItemQuoteBinding

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>() {
    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}