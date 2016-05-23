package com.dvidal.json;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    private Spinner spiAlunos;

    private TextView txtName;
    private TextView txtEmail;
    private TextView txtAge;
    private TextView txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("general", String.valueOf(this.loadAlunos().size()));

        // Instâncias
        spiAlunos = (Spinner) findViewById(R.id.spinnerAlunos);

        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtAge = (TextView) findViewById(R.id.txtAge);
        txtPhone = (TextView) findViewById(R.id.txtPhone);

        ArrayAdapter adapter = new ArrayAdapter<Aluno>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                loadAlunos()
        );

        spiAlunos.setAdapter(adapter);
        spiAlunos.setOnItemSelectedListener(this);

    }

    private List loadAlunos(){

        List<Aluno> listAlunos = new ArrayList<>();

        try {
            JSONArray jsonAlunos = new JSONArray(loadJSONFile());

            for (int i=0; i < jsonAlunos.length(); i++ ) {
                JSONObject alu = jsonAlunos.getJSONObject(i);
                Aluno newAluno = new Aluno(alu.getString("nome"), alu.getString("email"), alu.getInt("idade"), alu.getString("telefone"));
                listAlunos.add(newAluno);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listAlunos;
    }

    private String loadJSONFile(){

        InputStream is = getResources().openRawResource(R.raw.alunos);

        ByteArrayOutputStream output = new ByteArrayOutputStream();

        int i;

        try {
            i = is.read();
            while (i != -1){
                output.write(i);
                i = is.read();
            }

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Aluno alu = (Aluno) parent.getItemAtPosition(position);

        txtName.setText(alu.getName());
        txtEmail.setText(alu.getEmail());
        txtAge.setText(String.valueOf(alu.getAge()));
        txtPhone.setText(alu.getPhone());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
