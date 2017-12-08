package com.dvidal.bancodedados;

/**
 * Created by diegovidal on 22/05/16.
 */
public class Student {

    private long id;
    private String name;
    private String email;
    private String phone;

    public Student(long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Student(String name, String email, String phone) {
        this.id = 0;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    @Override
    public String toString() {
        return this.name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
