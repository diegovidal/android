package com.dvidal.snippet.repository;

import android.util.Log;

import com.dvidal.snippet.repository.model.Book;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
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

    @Override
    public Single<List<Book>> getBooksReactively() {

//        return Single.just(getBooks());

        return Single.fromCallable(() -> {
            try {
                System.out.println("Thread db: " + Thread.currentThread().getId());
//                Log.d("test", "Thread db: " + Thread.currentThread().getId());
                //Since, getBooks() is now not called on UI thread, therefore
                Realm realm = Realm.getDefaultInstance();
                RealmResults<Book> results = realm.where(Book.class).findAll();
                List<Book> bookList = realm.copyFromRealm(results);
                realm.close();
                return bookList;
            } catch (Exception e) {
                //for Episode 6 implementation, realm throws
                //java.lang.IllegalStateException: Realm access from incorrect thread. Realm objects can only be accessed on the thread they were created.
                //therefore provided new implementation
                throw new RuntimeException(e);
            }
        });
    }
}
