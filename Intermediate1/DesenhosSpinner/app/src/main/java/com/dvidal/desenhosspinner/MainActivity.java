package com.dvidal.desenhosspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private ImageView imgDesenho;

    private String[] aDesenhos = {"Capitão Planeta", "Caverna do Dragão", "Pole Position"};
    private int[] aResources = {R.drawable.ic_capitao_planeta, R.drawable.ic_caverna_do_dragao, R.drawable.ic_pole_position};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        imgDesenho = (ImageView) findViewById(R.id.imgDesenho);

        //imgDesenho.setImageResource(R.drawable.ic_capitao_planeta);

        spinner.setAdapter(getDesenhosAdapter());

        //spinner.setOnItemClickListener(this);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        imgDesenho.setImageResource(aResources[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private ArrayAdapter<String> getDesenhosAdapter(){
        ArrayAdapter<String> adptDesenhos = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, aDesenhos);

        return adptDesenhos;
    }
}
