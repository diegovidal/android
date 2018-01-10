package com.dvidal.snippet.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dvidal.snippet.presenter.BooksActivityPresenter;
import com.dvidal.snippet.repository.BooksRepository;
import com.dvidal.snippet.repository.DatabaseBooksRepository;
import com.dvidal.snippet.view.BooksActivityView;
import com.dvidal.snippet.R;
import com.dvidal.snippet.repository.model.Book;

import java.util.List;

public class BooksActivity extends AppCompatActivity implements BooksActivityView {

    BooksActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BooksActivityView booksActivityView = this;
        BooksRepository booksRepository = new DatabaseBooksRepository();
        presenter = new BooksActivityPresenter(booksActivityView, booksRepository);
        presenter.loadBooks();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //______________________________________________________________________________________________
    @Override
    public void displayBooks(List<Book> bookList) {
        ((TextView)findViewById(R.id.txtView)).setText(R.string.books_found);
    }

    @Override
    public void displayNoBooks() {
        ((TextView)findViewById(R.id.txtView)).setText(R.string.no_books_found);
    }
}
