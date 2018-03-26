package com.dvidal.snippet.presenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by diegovidal on 21/01/2018.
 */

public class BasePresenter {

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void unsubscribe(){

        compositeDisposable.clear();
    }

}
