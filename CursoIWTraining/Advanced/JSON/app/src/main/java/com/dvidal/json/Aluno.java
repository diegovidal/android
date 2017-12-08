package com.dvidal.json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegovidal on 21/05/16.
 */
public class Aluno {

    static public List<Aluno> listAlunos = new ArrayList<Aluno>();

    private String name;
    private String email;
    private int age;
    private String phone;

    public Aluno(String n, String e, int a, String p){

        this.name = n;
        this.email = e;
        this.age = a;
        this.phone = p;

        listAlunos.add(this);
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
