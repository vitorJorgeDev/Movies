package com.vitorjorge.movies.movies.model;

/**
 * Created by vitorjorge on 10/03/17.
 */

public class Movies {

    public static String TABLES = "MOVIES";
    public static String NAME = "NAME";
    public static String NACIONALITY = "NACIONALITY";
    public static String TYPE  = "TYPE";
    public static String AGE = "AGE";
    public static String ID = "_id";
    private long id;

    public Movies(){



        id = 0;
    }


    private String name;
    private String age;
    private String nacionality;
    private String type;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
