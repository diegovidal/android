package com.dvidal.snippet.repository;

import com.dvidal.snippet.repository.model.Book;

import java.util.List;

/**
 * Created by diegovidal on 08/01/2018.
 */

public interface BooksRepository {

    List<Book> getBooks();
}
