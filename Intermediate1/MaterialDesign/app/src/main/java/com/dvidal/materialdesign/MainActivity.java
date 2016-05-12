package com.dvidal.materialdesign;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private FloatingActionButton btnEmail;
    private Button btn;
    private SeekBar seekBar;

    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmail = (FloatingActionButton) findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(this);

        btn = (Button) findViewById(R.id.btnId);
        seekBar = (SeekBar) findViewById(R.id.seekBarId);

        seekBar.setOnSeekBarChangeListener(this);

        cardView = (CardView) findViewById(R.id.cardAlunoId);
    }

    @Override
    public void onClick(View v) {
        Log.d("General", "Funfou!");
    }



    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        // Não compatível
        //btn.setElevation(progress);

        // Compatível para todas as versões
        ViewCompat.setElevation(btn, progress);
        cardView.setRadius(progress);

        Log.d("General", "Valor do seekBar: "+ progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
