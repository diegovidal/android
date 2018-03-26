package com.dvidal.snippet.di;

import com.dvidal.snippet.activity.BooksActivity;
import com.dvidal.snippet.infrastructure.MyApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by diegovidal on 23/01/2018.
 */

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {

    void inject(MyApplication application);
    void inject(BooksActivity booksActivity);
}
