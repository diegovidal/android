package com.dvidal.snippet;

import com.dvidal.snippet.presenter.BooksActivityPresenter;
import com.dvidal.snippet.repository.model.Book;
import com.dvidal.snippet.repository.BooksRepository;
import com.dvidal.snippet.view.BooksActivityView;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Created by diegovidal on 08/01/2018.
 */
public class BooksActivityPresenterTest {

    @Test
    public void shouldPassBooksToView(){

        // given
        BooksActivityView view = new MockView();
        BooksRepository booksRepository = new MockBooksRepository(true);
//        BooksRepository booksRepository = new BooksRepository() {
//            @Override
//            public List<Book> getBooks() {
//                return Arrays.asList(new Book(), new Book(), new Book());
//            }
//        };

        // when
        BooksActivityPresenter presenter = new BooksActivityPresenter(view, booksRepository);
        presenter.loadBooks();

        // then
        org.junit.Assert.assertEquals(true, ((MockView) view).displayBooksWithBooksCalled);
    }

    @Test
    public void shouldHandleNoBooksFound() {

        // given
        BooksActivityView view = new MockView();
        BooksRepository booksRepository = new MockBooksRepository(false);
//        BooksRepository booksRepository = new BooksRepository() {
//            @Override
//            public List<Book> getBooks() {
//                return Collections.emptyList();
//            }
//        };

        // when
        BooksActivityPresenter presenter = new BooksActivityPresenter(view, booksRepository);
        presenter.loadBooks();

        // then
        org.junit.Assert.assertEquals(true, ((MockView) view).displayBooksWithNoBooksCalled);
    }

    private class MockView implements BooksActivityView {

        boolean displayBooksWithBooksCalled;
        boolean displayBooksWithNoBooksCalled;

        @Override
        public void displayBooks(List<Book> bookList) {

            displayBooksWithBooksCalled = true;
        }

        @Override
        public void displayNoBooks() {

            displayBooksWithNoBooksCalled = true;
        }
    }

    private class MockBooksRepository implements BooksRepository {

        private boolean returnSomeBooks;

        public MockBooksRepository(boolean returnSomeBooks) {
            this.returnSomeBooks = returnSomeBooks;
        }

        @Override
        public List<Book> getBooks() {

            if (returnSomeBooks)
                return Arrays.asList(new Book(), new Book(), new Book());

            return Collections.emptyList();
        }
    }

}