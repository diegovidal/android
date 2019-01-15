package br.com.dvidal.mvvmbasicstest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author diegovidal on 26/09/2018.
 */
class FakeQuoteDao {

    private val quoteList = mutableListOf<Quote>()
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.value = quoteList
    }

    fun addQuote(quote: Quote) {

        quoteList.add(quote)
        quotes.value = quoteList
    }

    fun fetchQuotes() = quotes as LiveData<List<Quote>>
}