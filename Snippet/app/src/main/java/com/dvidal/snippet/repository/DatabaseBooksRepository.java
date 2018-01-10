package com.dvidal.snippet.repository;

import com.dvidal.snippet.repository.model.Book;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by diegovidal on 10/01/2018.
 */

public class DatabaseBooksRepository implements BooksRepository {

    private Realm realm;

    public DatabaseBooksRepository() {
        realm = Realm.getDefaultInstance();
        //dummy book insertion
//        insertNewBook();
    }

    //dummy book
    private void insertNewBook() {
        Book book = new Book();
        book.setId(1);
        book.setName("Refactor Android App series overview");
        realm.beginTransaction();
        realm.copyToRealm(book);
        realm.commitTransaction();
    }

    @Override
    public List<Book> getBooks() {
        RealmResults<Book> results = realm.where(Book.class).findAll();
        return realm.copyFromRealm(results);
    }
}
