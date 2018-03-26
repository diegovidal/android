package com.dvidal.snippet.activity;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.dvidal.snippet.infrastructure.MyApplication;
import com.dvidal.snippet.presenter.BooksActivityPresenter;
import com.dvidal.snippet.repository.BooksRepository;
import com.dvidal.snippet.repository.DatabaseBooksRepository;
import com.dvidal.snippet.view.BooksActivityView;
import com.dvidal.snippet.R;
import com.dvidal.snippet.repository.model.Book;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class BooksActivity extends AppCompatActivity implements BooksActivityView {

    @Inject
    BooksRepository booksRepository;

    BooksActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        ((MyApplication) getApplication()).getAppComponent().inject(this);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new BooksActivityPresenter(this, booksRepository, AndroidSchedulers.mainThread());
        presenter.loadBooksReactively();

        System.out.println("Main Thread id is: "+ Looper.getMainLooper().getThread().getId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unsubscribe();
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

    @Override
    public void displayError() {

        Toast.makeText(this, "Error accessing data", Toast.LENGTH_SHORT).show();
    }
}
