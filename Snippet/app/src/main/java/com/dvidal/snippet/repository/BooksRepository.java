package com.dvidal.snippet.repository;

import com.dvidal.snippet.repository.model.Book;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by diegovidal on 08/01/2018.
 */

public interface BooksRepository {

    List<Book> getBooks();
    Single<List<Book>> getBooksReactively();
}
