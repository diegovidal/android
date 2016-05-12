package com.dvidal.navegacaobasica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Definição dos componentes
    private EditText txtName;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.mainScreen);

        txtName = (EditText) findViewById(R.id.txtName);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Log.d(">>>>>>>", "CLICOU");
        Log.d(">>>>>>>", txtName.getText().toString());

        // Faz referência da tela
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

        // Define as informações que serão passadas
        Bundle params = new  Bundle();
        params.putString("name", txtName.getText().toString());

        // Joga na intent
        intent.putExtras(params);

        // Outro modo de jogar na intent
        intent.putExtra("sobrenome", "Silva");

        // Joga pra tela
        startActivity(intent);
    }

}
