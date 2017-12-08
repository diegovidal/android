package com.dvidal.navegacaobasica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Aplica a navigation bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView txtResult = (TextView) findViewById(R.id.txtResult);

        Bundle params = getIntent().getExtras();
        String name = params.getString("name");

        txtResult.setText("Bem vindo "+ name + " - " + getIntent().getStringExtra("sobrenome"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.d(">>>>>>>", "Entrou");
        finish();

        return super.onOptionsItemSelected(item);
    }
}
