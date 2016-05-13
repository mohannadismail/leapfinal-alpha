package com.leap_app.leap.Models;

/**
 * Created by RamyFRadwan on 04/04/2016.
 */
public class User {

    public String email;
    public String name;

    public User(String email, String name){
        this.email = email;
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
