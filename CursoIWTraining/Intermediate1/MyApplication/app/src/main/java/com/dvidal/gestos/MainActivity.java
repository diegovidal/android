package com.dvidal.gestos;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtAcao;
    private TextView txtDetalhes;
    private TextView txtPosicoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAcao = (TextView) findViewById(R.id.txtAcaoId);
        txtDetalhes = (TextView) findViewById(R.id.txtDetalhesId);
        txtPosicoes = (TextView) findViewById(R.id.txtPosicoesId);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        String texto = "";
        boolean disparo = false;

        int e = MotionEventCompat.getActionMasked(event);

        switch (e) {

            case MotionEvent.ACTION_DOWN:
                texto = "Touch DOWN";
                disparo = true;
                break;

            case MotionEvent.ACTION_UP:
                texto = "Touch UP";
                disparo = true;
                break;

            case MotionEvent.ACTION_MOVE:
                texto = "Move";
                disparo = true;
                break;
        }

        txtAcao.setText(texto);
        txtDetalhes.setText("Toque(s) na tela: "+ event.getPointerCount());
        txtPosicoes.setText("Posição X: "+event.getX()+ " - Posicão Y: "+ event.getY());

        return disparo;
    }
}
