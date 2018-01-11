package com.dvidal.snippet;

import com.dvidal.snippet.presenter.BooksActivityPresenter;
import com.dvidal.snippet.repository.BooksRepository;
import com.dvidal.snippet.repository.model.Book;
import com.dvidal.snippet.view.BooksActivityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by diegovidal on 10/01/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class MockitoBooksActivityPresenterTest {

    private final List<Book> MANY_BOOKS = Arrays.asList(new Book(), new Book(), new Book());
    private final List<Book> EMPTY_BOOKS = Collections.emptyList();

    @Mock
    private BooksRepository booksRepository;

    @Mock
    private BooksActivityView view;

    private BooksActivityPresenter presenter;
    @Before
    public void setUp(){
        presenter = new BooksActivityPresenter(view, booksRepository);
    }

    @Test
    public void shouldPassBooksToView(){

        // given
        when(booksRepository.getBooks()).thenReturn(MANY_BOOKS);

        // when
        presenter.loadBooks();

        // then
        verify(view).displayBooks(MANY_BOOKS);
    }

    @Test
    public void shouldHandleNoBooksFound() {

        // given
        when(booksRepository.getBooks()).thenReturn(EMPTY_BOOKS);

        // when
        presenter.loadBooks();

        // then
        verify(view).displayNoBooks();
    }
}
