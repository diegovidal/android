package com.dvidal.snippet.repository.model;

import io.realm.RealmObject;

/**
 * Created by diegovidal on 08/01/2018.
 */

public class Book extends RealmObject {

    private long id;
    private String name;

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
}
