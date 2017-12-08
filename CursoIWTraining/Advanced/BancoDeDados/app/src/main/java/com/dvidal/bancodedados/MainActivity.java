package com.dvidal.bancodedados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Spinner spiStudent;

    private EditText txtId;
    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPhone;

    private Button btnSave;
    private Button btnDelete;
    private Button btnNew;


    private  StudentDAO studentDAO;
    private List<Student> listAlunos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentDAO = new StudentDAO(this);

        // Carrega os valores na List
        listAlunos = studentDAO.select(null);

        // Altera um documento
//        Student student = listAlunos.get(0);
//        student.setName("João");
//        studentDAO.save(student);

        // Deleta um documento
        //studentDAO.delete(listAlunos.get(0));

//        // Instância um documento
//        Student s1 = new Student("Diego Vidal", "diegovidal08@gmail.com", "985251091");
//        Student s2 = new Student("Victor Vidal", "victorvidal@gmail.com", "943848343");
//        Student s3 = new Student("Zuleuda Vidal", "zuzu@gmail.com", "987646363");
////
//        studentDAO.save(s1);
//        studentDAO.save(s2);
//        studentDAO.save(s3);

//
//        Log.d("General", String.valueOf(studentDAO.select("").toString()));
//
        // Instâncias UI
        spiStudent = (Spinner) findViewById(R.id.spiStudents);
        spiStudent.setOnItemSelectedListener(this);
        refreshSpinner();

        txtId = (EditText) findViewById(R.id.txtId);
        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPhone = (EditText) findViewById(R.id.txtPhone);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

        btnNew = (Button) findViewById(R.id.btnNew);
        btnNew.setOnClickListener(this);


    }

    private void refreshSpinner(){

        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                studentDAO.select(null)
        );

        spiStudent.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        long id = 0;
        if (spiStudent.getAdapter().getCount() > 0){
            id = Long.parseLong(txtId.getText().toString());
        }

        Student s = new Student(
            id,
            txtName.getText().toString(),
            txtEmail.getText().toString(),
            txtPhone.getText().toString()
        );

        switch (v.getId()){

            case R.id.btnSave:
                studentDAO.save(s);
                break;

            case R.id.btnDelete:
                studentDAO.delete(s);
                break;

            case R.id.btnNew:
                s.setId(0);
                studentDAO.save(s);
                break;

        }

        refreshSpinner();
        clearFields();
    }

    private void clearFields(){
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Student student = (Student) parent.getItemAtPosition(position);

        txtId.setText(String.valueOf(student.getId()));
        txtName.setText(student.getName());
        txtEmail.setText(student.getEmail());
        txtPhone.setText(student.getPhone());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
