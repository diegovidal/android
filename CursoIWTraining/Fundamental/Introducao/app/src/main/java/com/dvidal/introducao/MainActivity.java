package com.dvidal.introducao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String nome = "Diego Vidal";
        int idade = 24;
        float altura = 1.3f;
        double largura = 0.92;
        boolean aprovado = true;

        String novelas[] = {"Maria do Bairro", "Marimar", "A Usurpadora"};

        Log.d("Meu Primeiro Log", "Que legal");
        Log.d("Meu Primeiro Log", nome);

        // ArrayList
        ArrayList<String> novelasGlobo = new ArrayList<>();
        novelasGlobo.add("Vamp");
        novelasGlobo.add("O Rei do Gado");
        novelasGlobo.add("Torre de Babel");

        Log.d("ArrayList", novelasGlobo.get(1));

        // HashMap
        Map<String, String> estados = new HashMap<>();
        estados.put("CE", "Ceará");
        estados.put("RN", "Rio Grande do Norte");
        estados.put("SP", "São Paulo");

        // Bundle
        Bundle pacote = new Bundle();
        pacote.putString("CE", "Ceará");
        pacote.putString("RN", "Rio Grande do Norte");

        dizOi();
        Log.d("Func", dizOla());

        //L teste = new L();
        L.d("Teste");
    }

    public void dizOi(){

        Log.d("Func", "Oieeee!");
    }

    public String dizOla(){
        return "Olááááá!";
    }
}
