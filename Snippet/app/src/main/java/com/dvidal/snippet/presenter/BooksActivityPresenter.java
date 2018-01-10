package com.dvidal.snippet.presenter;

import com.dvidal.snippet.view.BooksActivityView;
import com.dvidal.snippet.repository.model.Book;
import com.dvidal.snippet.repository.BooksRepository;

import java.util.List;

/**
 * Created by diegovidal on 08/01/2018.
 */

public class BooksActivityPresenter {

    private BooksActivityView view;
    private BooksRepository booksRepository;

    public BooksActivityPresenter(BooksActivityView view, BooksRepository booksRepository) {

        this.view = view;
        this.booksRepository = booksRepository;
    }


    public void loadBooks() {

        List<Book> bookList = booksRepository.getBooks();
        if(bookList.isEmpty())
            view.displayNoBooks();
        else
            view.displayBooks(bookList);
    }
}
