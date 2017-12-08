package com.dvidal.xml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerAlunos;

    private TextView txtName;
    private TextView txtEmail;
    private TextView txtAge;
    private TextView txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerAlunos = (Spinner) findViewById(R.id.spinnerId);

        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtAge = (TextView) findViewById(R.id.txtAge);
        txtPhone = (TextView) findViewById(R.id.txtPhone);

        InputStream input = getResources().openRawResource(R.raw.alunos);
        List retorno = null;

        try {
            retorno = parseXML(input);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter adapter = new ArrayAdapter<Aluno>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                retorno
        );

        spinnerAlunos.setAdapter(adapter);
        spinnerAlunos.setOnItemSelectedListener(this);
    }

    private List parseXML(InputStream is) throws XmlPullParserException, IOException{

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(is, null);
            parser.nextTag();

            return loadAlunos(parser);
        }
        finally {
            is.close();
        }

    }

    private List loadAlunos(XmlPullParser parser) throws XmlPullParserException, IOException {

        List<Aluno> alunos = new ArrayList<>();

        parser.require(XmlPullParser.START_TAG, null, "alunos");
        while (parser.next() != XmlPullParser.END_TAG){

            if (parser.getEventType() != XmlPullParser.START_TAG) continue;

            String tag = parser.getName();
            if (tag.equals("aluno")){
                alunos.add(loadNewAluno(parser, tag));
            }
        }


        return alunos;
    }

    private Aluno loadNewAluno(XmlPullParser parser, String tag) throws XmlPullParserException, IOException{

        parser.require(XmlPullParser.START_TAG, null, tag);

        String name = null;
        String email = null;
        String age = null;
        String phone = null;

        while (parser.next() != XmlPullParser.END_TAG){

            if (parser.getEventType() != XmlPullParser.START_TAG) continue;

            String tagInterna = parser.getName();
            if(tagInterna.equals("nome")) name = loadElement(parser, "nome");
            if(tagInterna.equals("email")) email = loadElement(parser, "email");
            if(tagInterna.equals("idade")) age = loadElement(parser, "idade");
            if(tagInterna.equals("telefone")) phone = loadElement(parser, "telefone");
        }

        Aluno aluno = new Aluno(name, email, age, phone);
        return aluno;
    }

    private String loadElement(XmlPullParser parser, String tagElement) throws XmlPullParserException, IOException{

        parser.require(XmlPullParser.START_TAG, null, tagElement);
        String content = getElementText(parser);
        parser.require(XmlPullParser.END_TAG, null, tagElement);

        return content;

    }

    private String getElementText(XmlPullParser parser) throws XmlPullParserException, IOException{
        String resultado = "";

        if (parser.next() == XmlPullParser.TEXT){

            resultado = parser.getText();
            parser.next();
        }

        return resultado;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Aluno aluno = (Aluno) parent.getItemAtPosition(position);

        txtName.setText(aluno.getName());
        txtEmail.setText(aluno.getEmail());
        txtAge.setText(aluno.getAge());
        txtPhone.setText(aluno.getPhone());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
