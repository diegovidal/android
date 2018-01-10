package com.dvidal.snippet.view;

import com.dvidal.snippet.repository.model.Book;

import java.util.List;

/**
 * Created by diegovidal on 08/01/2018.
 */

public interface BooksActivityView {

    void displayBooks(List<Book> bookList);
    void displayNoBooks();
}
