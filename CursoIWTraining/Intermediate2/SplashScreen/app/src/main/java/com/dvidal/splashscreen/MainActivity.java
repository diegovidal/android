package com.dvidal.splashscreen;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnProgress;
    private Button btnSapecar;

    private HandlerDeTeste myHandler;
    private SeekBar mySkb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProgress = (Button) findViewById(R.id.btnProgress);
        btnProgress.setOnClickListener(this);

        btnSapecar = (Button) findViewById(R.id.btnSapecar);
        btnSapecar.setOnClickListener(this);

        mySkb = (SeekBar) findViewById(R.id.skb);

        myHandler = new HandlerDeTeste();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnProgress){
            v.setEnabled(false);
            mySkb.setProgress(0);
            disparaProgresso();

        }
        else if (v.getId() == R.id.btnSapecar){
            new Thread() {
                @Override
                public void run() {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            btnSapecar.setEnabled(false);
                        }
                    });

                }
            }.start();

        }
    }

    private void disparaProgresso(){
        Thread t = new Thread() {
            @Override
            public void run() {

                Message msg = new Message();
                msg.what = 2;
                myHandler.sendMessageDelayed(msg, 1000);
            }
        };

        t.start();
    }

    private class HandlerDeTeste extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                Toast.makeText(MainActivity.this, "Muito bom!", Toast.LENGTH_SHORT).show();
            }
            else if(msg.what == 2){
                int currentProgress = mySkb.getProgress();
                if (currentProgress < 100){
                    mySkb.setProgress(currentProgress + 5);
                    disparaProgresso();
                }
                else{
                    btnProgress.setEnabled(true);
                }

            }
        }
    }
}
