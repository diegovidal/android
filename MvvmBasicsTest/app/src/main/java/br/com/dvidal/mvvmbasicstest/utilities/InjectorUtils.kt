package br.com.dvidal.mvvmbasicstest.utilities

import br.com.dvidal.mvvmbasicstest.data.FakeDatabase
import br.com.dvidal.mvvmbasicstest.data.QuoteRepository
import br.com.dvidal.mvvmbasicstest.ui.quotes.QuotesViewModelFactory

/**
 * @author diegovidal on 26/09/2018.
 */
object InjectorUtils {

    fun provideQuotesViewModelFactory(): QuotesViewModelFactory {

        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}