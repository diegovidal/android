package com.dvidal.snippet;

import com.dvidal.snippet.presenter.BooksActivityPresenter;
import com.dvidal.snippet.repository.BooksRepository;
import com.dvidal.snippet.repository.model.Book;
import com.dvidal.snippet.view.BooksActivityView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.*;

/**
 * Created by diegovidal on 10/01/2018.
 */

public class MockitoBooksActivityPresenterTest {

    private final List<Book> MANY_BOOKS = Arrays.asList(new Book(), new Book(), new Book());
    private final List<Book> EMPTY_BOOKS = Collections.emptyList();

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private BooksRepository booksRepository;

    @Mock
    private BooksActivityView view;

    private BooksActivityPresenter presenter;
    @Before
    public void setUp(){

        presenter = new BooksActivityPresenter(view, booksRepository, Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @After
    public void cleanUp(){

        RxJavaPlugins.reset();
    }

    @Test
    public void shouldPassBooksToView(){

        // given
//        when(booksRepository.getBooks()).thenReturn(MANY_BOOKS);
        when(booksRepository.getBooksReactively()).thenReturn(Single.just(MANY_BOOKS));

        // when
//        presenter.loadBooks();
        presenter.loadBooksReactively();

        // then
        verify(view).displayBooks(MANY_BOOKS);
    }

    @Test
    public void shouldHandleNoBooksFound() throws InterruptedException {

        // given
//        when(booksRepository.getBooks()).thenReturn(EMPTY_BOOKS);
        when(booksRepository.getBooksReactively()).thenReturn(Single.just(EMPTY_BOOKS));

        // when
//        presenter.loadBooks();
        presenter.loadBooksReactively();

//        Thread.sleep(1000); // NEVER DO THIS

        // then
        verify(view).displayNoBooks();
    }

    @Test
    public void shouldHandlerError() throws InterruptedException {

        //given
//        when(booksRepository.getBooks()).thenThrow(new Throwable("boom!"));
        when(booksRepository.getBooksReactively()).thenReturn(Single.<List<Book>>error(new Throwable("Bum!")));

        //when
//        presenter.loadBooks();
        presenter.loadBooksReactively();

//        Thread.sleep(1000); // NEVER DO THIS

        //then
        verify(view).displayError();
    }
}
