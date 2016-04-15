package com.sandstorm.softspec.queue2win.Models;

/**
 * Created by FTTX on 4/15/2016 AD.
 */
public class Food {

    private String name;
    private int price;

    public Food(String name,int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
