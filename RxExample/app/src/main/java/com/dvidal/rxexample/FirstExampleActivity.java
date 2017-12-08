package com.dvidal.rxexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FirstExampleActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_example);

        // UI
        mRecyclerView = findViewById(R.id.activity_first_example_recyclerView);
        mAdapter = new SimpleAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        createObservable();
    }

    private void createObservable(){

        // RxJava is about observables and observers
        Observable<List<String>> listObservable = Observable.just(getDeserts());
        listObservable.subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<String> strings) {

                mAdapter.setStringList(strings);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private List<String> getDeserts(){

        ArrayList<String> deserts = new ArrayList<>();

        deserts.add("Cupcake");
        deserts.add("Ice Cream");
        deserts.add("Kitcat");
        deserts.add("Gingerbread");

        return deserts;
    }
}
