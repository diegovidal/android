package com.dvidal.rxexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by diegovidal on 13/11/2017.
 */

public class SecondExampleActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ProgressBar mProgressBar;
    SimpleAdapter mAdapter;

    SongClient mSongClient;
    Disposable mDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_example);

        mSongClient = new SongClient();

        mRecyclerView = findViewById(R.id.activity_second_example_recyclerView);
        mAdapter = new SimpleAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar = findViewById(R.id.activity_second_example_progressBar);
        createSecondObservable();
    }

    private void createFirstObservable(){

        Observable<List<String>> songsObservable = Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return mSongClient.getSongs();
            }
        });

        mDisposable = songsObservable // Observable
                .subscribeOn(Schedulers.io()) // Move the observable to a different thread
                .observeOn(AndroidSchedulers.mainThread()) // Allows us to observe/manipulate value on a different thread
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> songs) throws Exception {

                        mAdapter.setStringList(songs);
                        mProgressBar.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void createSecondObservable(){

        Observable<List<String>> songsObservable = Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return mSongClient.getSongs();
            }
        });

        mDisposable = songsObservable // Observable
                .subscribeOn(Schedulers.io()) // Move the observable to a different thread
                .observeOn(AndroidSchedulers.mainThread()) // Allows us to observe/manipulate value on a different thread
                .map(new Function<List<String>, List<String>>() {

                    @Override
                    public List<String> apply(List<String> strings) throws Exception {

                        List<String> newList = new ArrayList<>();
                        String newString;
                        int index = 0;

                         for (String string: strings){
                            newString = string + "this is song number "+ index;
                            newList.add(newString);
                            index ++;
                        }

                        return newList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> songs) throws Exception {

                        mAdapter.setStringList(songs);
                        mProgressBar.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && mDisposable.isDisposed()){
            mDisposable.dispose();
        }
    }

}
