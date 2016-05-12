package com.dvidal.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPrimeiro;
    private Button btnSegundo;
    private Button btnTerceiro;

    private Fragment[] fragments = {new PrimeiroFragment(), new SegundoFragment(), new TerceiroFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrimeiro = (Button) findViewById(R.id.btnPrimeiroId);
        btnSegundo = (Button) findViewById(R.id.btnSegundoId);
        btnTerceiro = (Button) findViewById(R.id.btnTerceiroId);

        btnPrimeiro.setOnClickListener(this);
        btnSegundo.setOnClickListener(this);
        btnTerceiro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (v.getId()){

            case R.id.btnPrimeiroId:
                ft.replace(R.id.fragContainer, fragments[0], "Primeiro");
                break;

            case R.id.btnSegundoId:
                ft.replace(R.id.fragContainer, fragments[1], "Segundo");
                break;

            case R.id.btnTerceiroId:
                ft.replace(R.id.fragContainer, fragments[2], "Terceiro");
                break;

        }

        // Só chama o inflate do objeto após esse comando
        ft.commit();
    }
}
