package com.dvidal.rxexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by diegovidal on 13/11/2017.
 */

public class ThirdExampleActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mTxtView;

    private int mCounter;

    private PublishSubject<Integer> mCounterEmitter;

    // Subject - Is an object that is both an observable and observer
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_example);

        mButton = findViewById(R.id.activity_third_example_button);
        mTxtView = findViewById(R.id.activity_third_example_txtView);
        mCounter = 0;
        mTxtView.setText(String.valueOf(mCounter));

        createConterEmitter();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCounter ++;
                mCounterEmitter.onNext(mCounter);
            }
        });
    }

    private void createConterEmitter(){

        mCounterEmitter = PublishSubject.create();
        mCounterEmitter.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

                mTxtView.setText(String.valueOf(integer));
            }
        });
    }
}
