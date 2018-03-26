package com.dvidal.snippet.presenter;

import com.dvidal.snippet.view.BooksActivityView;
import com.dvidal.snippet.repository.model.Book;
import com.dvidal.snippet.repository.BooksRepository;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by diegovidal on 08/01/2018.
 */

public class BooksActivityPresenter extends BasePresenter {

    private BooksActivityView view;
    private BooksRepository booksRepository;
    private Scheduler mainScheduler;

    public BooksActivityPresenter(BooksActivityView view, BooksRepository booksRepository, Scheduler mainScheduler) {

        this.view = view;
        this.booksRepository = booksRepository;
        this.mainScheduler = mainScheduler;
    }


    public void loadBooks() {

        List<Book> bookList;
        try {
            bookList = booksRepository.getBooks();

            if(bookList.isEmpty())
                view.displayNoBooks();
            else
                view.displayBooks(bookList);
        } catch (Exception e) {
            view.displayError();
        }
    }

    public void loadBooksReactively() {

        compositeDisposable.add(booksRepository.getBooksReactively() // subscribeOn
                .subscribeOn(Schedulers.io())
                .observeOn(mainScheduler)
                .subscribeWith(new DisposableSingleObserver<List<Book>>() { // observeOn
                    @Override
                    public void onSuccess(List<Book> books) {

                        System.out.println("Thread subscribe: "+ Thread.currentThread().getId());
                        if (books.isEmpty())
                            view.displayNoBooks();
                        else
                            view.displayBooks(books);
                    }

                    @Override
                    public void onError(Throwable e) {

                        System.out.println("Thread subscribe error: "+ Thread.currentThread().getId());
                        view.displayError();
                    }
                }));

//        booksRepository.getBooksReactively()
//                .subscribe(books -> {
//
//                    if (books.isEmpty())
//                        view.displayNoBooks();
//                    else
//                        view.displayBooks(books);
//                }, throwable -> view.displayError());


    }
}
