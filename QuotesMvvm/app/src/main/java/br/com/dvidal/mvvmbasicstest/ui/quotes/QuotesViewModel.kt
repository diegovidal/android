package br.com.dvidal.mvvmbasicstest.ui.quotes

import androidx.lifecycle.ViewModel
import br.com.dvidal.mvvmbasicstest.data.Quote
import br.com.dvidal.mvvmbasicstest.data.QuoteRepository

/**
 * @author diegovidal on 26/09/2018.
 */
class QuotesViewModel(private val quoteRepository: QuoteRepository)
    : ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)
}