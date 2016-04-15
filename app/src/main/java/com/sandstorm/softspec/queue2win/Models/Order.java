package com.sandstorm.softspec.queue2win.Models;

/**
 * Created by FTTX on 4/15/2016 AD.
 */
public class Order {

    private Food food;

    private int amount;

    public Order(Food food, int amount) {
        this.food = food;
        this.amount = amount;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if(order.getFood().equals(food)) return true;
        else return false;

    }

    @Override
    public int hashCode() {
        int result = food != null ? food.hashCode() : 0;
        result = 31 * result + amount;
        return result;
    }

    public int getPrice() {
        return food.getPrice()*amount;
    }
}
