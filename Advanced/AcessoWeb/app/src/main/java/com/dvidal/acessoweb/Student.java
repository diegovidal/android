package com.dvidal.acessoweb;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by diegovidal on 21/05/16.
 */
public class Student {

    private String name;
    private String email;
    private int age;
    private String phone;

    public Student(JSONObject json){

        try {
            this.name = json.getString("nome");
            this.email = json.getString("email");
            this.age = json.getInt("idade");
            this.phone =json.getString("telefone");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
