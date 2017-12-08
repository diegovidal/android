package com.dvidal.gestos;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by diegovidal on 10/04/16.
 */
public class GestosActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat detectGesture;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setTitle("Gestos Activity");
        detectGesture = new GestureDetectorCompat(this, this);
        detectGesture.setOnDoubleTapListener(this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detectGesture.onTouchEvent(event);
    }

    // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

    @Override
    public boolean onDown(MotionEvent e) {

        Log.d("General", "Evento onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

        Log.d("General", "Evento onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        Log.d("General", "Evento onUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        Log.d("General", "Evento onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

        Log.d("General", "Evento OnLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        Log.d("General", "Evento onFling");
        return false;
    }


    // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {

        Log.d("General", "Evento Toque Simples");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        Log.d("General", "Evento Touch Duplo");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {

        Log.d("General", "Evento dentro do Double tap");
        return false;
    }

}