package com.sandstorm.softspec.queue2win.Models;

/**
 * Created by FTTX on 4/12/2016 AD.
 */
public class Customer {

    private String username;
    private String password;


    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public String getUsername() {

        return username;
    }
}