package br.com.dvidal.mvvmbasicstest.ui.quotes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.dvidal.mvvmbasicstest.R
import br.com.dvidal.mvvmbasicstest.data.Quote
import br.com.dvidal.mvvmbasicstest.utilities.InjectorUtils
import kotlinx.android.synthetic.main.quotes_main.*

class QuotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quotes_main)
        initializeUi()
    }

    private fun initializeUi() {

        val factory = InjectorUtils.provideQuotesViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
                .get(QuotesViewModel::class.java)

        viewModel.getQuotes().observe(this, Observer { quotes ->

            val stringBuilder = StringBuilder()
            quotes.forEach{ quote ->
                stringBuilder.append("$quote\n\n")
            }

            textView_quotes.text = stringBuilder.toString()
        })

        button_add_quote.setOnClickListener {

            val quoteText = editText_quote.text.toString()
            val author = editText_author.text.toString()

            Quote(quoteText, author).also { quote ->

                viewModel.addQuote(quote)
                editText_quote.setText("")
                editText_author.setText("")
            }
        }
    }
}
