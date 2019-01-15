package br.com.dvidal.mvvmbasicstest.data

/**
 * @author diegovidal on 26/09/2018.
 */
class FakeDatabase private constructor(){

    var quoteDao = FakeQuoteDao()
        private set

    companion object {

        @Volatile
        private var instance: FakeDatabase? = null

        fun getInstance() =
                instance ?: synchronized(this){
                    instance ?: FakeDatabase().also {
                        instance = it
                    }
                }
    }
}