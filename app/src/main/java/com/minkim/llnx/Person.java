package com.minkim.llnx;

/**
 * Created by roseanna on 4/10/16.
 */
public class Person {
    String name;
    int id;
    public Person(int id, String name){
        this.name = name;
        this.id = id;
    }

    public int getID(){
        return id;
    }
    public String getName(){
        return name;
    }
}
