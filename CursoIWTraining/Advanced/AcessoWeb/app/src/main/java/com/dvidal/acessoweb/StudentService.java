package com.dvidal.acessoweb;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegovidal on 22/05/16.
 */
public class StudentService{

    private static String URL = "http://www.iwtraining.com.br/aula/alunos.json";
    private StudentServiceListener studentServiceListener;

    StudentService(){

        ConnectionTask task = new ConnectionTask();
        task.execute();
    }

    public void setOnStudentServiceListener(StudentServiceListener listener){
        this.studentServiceListener = listener;
    }

    private class ConnectionTask extends AsyncTask<String,Void,String>{

        HttpURLConnection conn =null;
        String jsonTexto;
        @Override
        protected String doInBackground(String... params) {

            try {
                URL url =new URL(URL);
                Connection conexao = new Connection(url);
                conn = conexao.startConnection();

                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream is = conn.getInputStream();
                    jsonTexto = Utils.bytesToString(is);
                }

            }catch (IOException e){
                e.printStackTrace();
            }

            return jsonTexto;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(">>>", s);

            try {
                JSONArray jsonStudents = new JSONArray(s);
                List<Student> students = new ArrayList<>();

                for (int i=0; i < jsonStudents.length(); i++ ) {
                    JSONObject studentObject = jsonStudents.getJSONObject(i);
                    Student newStudent = new Student(studentObject);
                    students.add(newStudent);
                }

                studentServiceListener.onStudentServiceSucessfully(students);
            } catch (JSONException e) {
                //e.printStackTrace();
                studentServiceListener.onStudentServiceFailure(e.getMessage());
            }


        }
    }

    public interface StudentServiceListener{

        void onStudentServiceSucessfully(List<Student> students);
        void onStudentServiceFailure(String error);
    }
}
