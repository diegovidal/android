package com.dvidal.persistenciaarquivo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtMessage;

    private Button btnRecord;
    private Button btnClear;
    private Button btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instâncias
        txtMessage = (EditText) findViewById(R.id.txtMessage);

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
                record();
                break;

            case R.id.btnClear:
                clear();
                break;

            case R.id.btnLoad:
                load();
                break;
        }
    }

    private void record(){

        try {
            FileOutputStream output = openFileOutput("my_file.txt", MODE_PRIVATE);
            whiteFile(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void clear(){

        txtMessage.setText("");
    }

    private void load(){


        try {
            FileInputStream input = openFileInput("my_file.txt");
            readFile(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void whiteFile(FileOutputStream fos) throws IOException{

        String[] lines = TextUtils.split(txtMessage.getText().toString(), "\n");
        PrintWriter writter = new PrintWriter(fos);
        for (String l: lines) {
            writter.println(l);
        }

        writter.flush(); // fecha processo
        writter.close(); // fecha o aquivo
        fos.close();     // fecha o output stream
    }

    private void readFile(FileInputStream fis) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null ){
            if (sb.length() != 0) sb.append("\n");
            sb.append(line);
        }

        reader.close();
        fis.close();

        txtMessage.setText(sb.toString());
    }
}
