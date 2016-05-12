package com.dvidal.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    private Handler meuHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        meuHandler = new Handler();
        fazTransicao();

    }

    private void fazTransicao(){

        meuHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // Faz referência da tela
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                // Joga pra tela
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

}
