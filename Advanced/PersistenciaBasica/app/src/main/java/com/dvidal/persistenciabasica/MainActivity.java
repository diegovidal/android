package com.dvidal.persistenciabasica;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName;
    private EditText txtEmail;

    private Button btnRecord;
    private Button btnClear;
    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instâncias
        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);

        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnLoad = (Button) findViewById(R.id.btnLoad);

        // Listeners
        btnRecord.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnLoad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnRecord:
                this.record();
                break;

            case R.id.btnClear:
                this.clear();
                break;

            case R.id.btnLoad:
                this.load();
                break;
        }
    }

    private void record(){
        SharedPreferences prefs = getSharedPreferences("geral", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("name", txtName.getText().toString());
        editor.putString("email", txtEmail.getText().toString());

        // Operações real time
        //editor.commit();

        // Operações normais
        editor.apply();

    }

    private void clear(){

        txtName.setText("");
        txtEmail.setText("");
    }

    private void load(){

        SharedPreferences prefs = getSharedPreferences("geral", MODE_PRIVATE);
        txtName.setText(prefs.getString("name", "nd"));
        txtEmail.setText(prefs.getString("email", "nd"));
    }
}
