package com.dvidal.xml;

/**
 * Created by diegovidal on 21/05/16.
 */
public class Aluno {

    private String name;
    private String email;
    private String age;
    private String phone;

    public Aluno(String n, String e, String a, String p){
        this.name = n;
        this.email = e;
        this.age = a;
        this.phone = p;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {

        return name;
    }

    public String getEmail() {

        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
