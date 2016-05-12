package com.dvidal.tasks;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnIniciar;
    private ProgressBar pgbTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        pgbTarefas = (ProgressBar) findViewById(R.id.pgbTarefas);

        btnIniciar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnIniciar:
                ProgressoTask pt = new ProgressoTask();
                pt.execute();
                break;
        }
    }

    private class ProgressoTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pgbTarefas.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                synchronized (this){
                    wait(3000);
                }
            } catch (InterruptedException e) {
                Log.d("Error", e.getMessage());
            }
            return "Que Louco!";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.d("General", s);
            pgbTarefas.setVisibility(View.INVISIBLE);
        }
    }
}
