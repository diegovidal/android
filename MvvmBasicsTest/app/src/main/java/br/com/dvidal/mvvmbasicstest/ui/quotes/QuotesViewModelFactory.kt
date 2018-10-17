package br.com.dvidal.mvvmbasicstest.ui.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.dvidal.mvvmbasicstest.data.QuoteRepository

/**
 * @author diegovidal on 26/09/2018.
 */
class QuotesViewModelFactory(private val quoteRepository: QuoteRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return QuotesViewModel(quoteRepository) as T
    }
}