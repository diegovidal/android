package com.dvidal.threads;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDisparar;
    private Button btnDispararPost;
    private Handler meuHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDisparar = (Button) findViewById(R.id.btnDisparar);
        btnDispararPost = (Button) findViewById(R.id.btnDispararPost);
        btnDisparar.setOnClickListener(this);
        btnDispararPost.setOnClickListener(this);

        meuHandler = new HandlerDeTeste();

    }

    // Button Implements
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDisparar) {
            Message minhaMensagem = new Message();
            minhaMensagem.what = 1;

            //meuHandler.sendMessage(minhaMensagem);
            meuHandler.sendMessageDelayed(minhaMensagem, 4000);

        }
        else if (v.getId() == R.id.btnDispararPost) {
            meuHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btnDispararPost.setEnabled(false);
                    Toast.makeText(MainActivity.this, "Muito bom!", Toast.LENGTH_SHORT).show();
                }
            }, 4000);
        }
    }

    private class HandlerDeTeste extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                Toast.makeText(MainActivity.this, "Muito bom!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
