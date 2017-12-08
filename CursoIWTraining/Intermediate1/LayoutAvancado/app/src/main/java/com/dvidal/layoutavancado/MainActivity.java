package com.dvidal.layoutavancado;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView txtEstado;
    private ImageButton btnSmile;
    private ImageButton btnBad;

    private String[] estados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instâncias
        txtEstado = (AutoCompleteTextView) findViewById(R.id.txtEstados);
        btnSmile = (ImageButton) findViewById(R.id.btnSmile);
        btnBad = (ImageButton) findViewById(R.id.btnBad);

        estados = new String[] {"Ceará", "Rio Grande do Norte", "Rio Grande do Sul", "Maranhão"};

        ArrayAdapter<String> adpEstados = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, estados);
        txtEstado.setAdapter(adpEstados);

        btnSmile.setOnClickListener(this);
        btnBad.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnSmile){
            exibirAlerta("Clicou no Smile");
        }
        else{
            exibirAlerta("Clicou no Bad");
        }
    }

    private void exibirAlerta(String msg){

        Log.d("General", msg);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.label_alert);
        builder.setMessage(msg);

        builder.setPositiveButton(R.string.label_alert_OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exibirAlerta("Clicou no Alert");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        //OR
        //builder.show();
    }
}
