package com.sandstorm.softspec.queue2win.Models;

/**
 * Created by FTTX on 4/12/2016 AD.
 */
public class Customer {

    private String username;
    private String password;
    private Queue queue;
    private int balance;


    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        queue = null;
        balance = 0;
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

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public Queue getQueue() {
        return queue;
    }

    public void deleteQueue() {
        queue = null;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdrawal(int amount) {
        this.balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}
