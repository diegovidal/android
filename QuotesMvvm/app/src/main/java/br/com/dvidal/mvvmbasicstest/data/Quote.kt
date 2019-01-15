package br.com.dvidal.mvvmbasicstest.data

/**
 * @author diegovidal on 26/09/2018.
 */
data class Quote(val quoteText: String,
                 val author: String) {

    override fun toString(): String {
        return "$quoteText - $author"
    }
}