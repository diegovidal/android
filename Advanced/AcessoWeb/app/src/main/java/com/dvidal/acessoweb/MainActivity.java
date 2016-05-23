package com.dvidal.acessoweb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, StudentService.StudentServiceListener {

    private Button btnConnect;
    private Spinner spinnerStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConnect = (Button)findViewById(R.id.btnConnect);

        btnConnect.setOnClickListener(this);

        spinnerStudents = (Spinner) findViewById(R.id.spinnerStudents);

    }


    @Override
    public void onClick(View v) {
        if (Connection.verifyConnection(this)){

            StudentService ss = new StudentService();
            ss.setOnStudentServiceListener(this);
        }

    }

    @Override
    public void onStudentServiceSucessfully(List<Student> students) {
        Log.d("General", "Deu certo!!");

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, students);
        spinnerStudents.setAdapter(adapter);
    }

    @Override
    public void onStudentServiceFailure(String error) {
        Log.d("General", error);
    }
}

