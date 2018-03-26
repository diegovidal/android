package com.dvidal.snippet.di;

import android.content.Context;

import com.dvidal.snippet.infrastructure.MyApplication;
import com.dvidal.snippet.repository.BooksRepository;
import com.dvidal.snippet.repository.DatabaseBooksRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by diegovidal on 23/01/2018.
 */

@Module
public class AppModule {

    private final MyApplication application;

    public AppModule(MyApplication app) {
        this.application = app;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    BooksRepository providesBooksRepository() {
        return new DatabaseBooksRepository();
    }
}
