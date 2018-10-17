package br.com.dvidal.mvvmbasicstest.data

/**
 * @author diegovidal on 26/09/2018.
 */
class QuoteRepository private constructor(private val quoteDao: FakeQuoteDao){

    fun addQuote(quote: Quote) {

        quoteDao.addQuote(quote)
    }

    fun getQuotes() = quoteDao.fetchQuotes()

    companion object {

        @Volatile
        private var instance: QuoteRepository? = null

        fun getInstance(quoteDao: FakeQuoteDao) =
                instance ?: synchronized(this) {
                    instance ?: QuoteRepository(quoteDao).also {
                        instance = it
                    }
                }
    }
}