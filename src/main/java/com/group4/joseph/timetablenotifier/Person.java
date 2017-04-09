package com.group4.joseph.timetablenotifier;

public class Person {

    private static final Person INSTANCE = new Person();
    public int person;
    public String email;

    public double lat;
    public double long1;

    public static Person getInstance() {
        return INSTANCE;
    }
}