package com.sandstorm.softspec.queue2win.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by FTTX on 4/12/2016 AD.
 */
public class Queue {

    private String name;
    private long timeStart;
    private long timeReady;
    private Date date;
    private final int queueTime = 10;
    /**
     * Will become Map later on
     */
    private List<Order> orderList;

    public Queue(String name) {
        this.name = name;
        date = new Date();
        timeStart = date.getMinutes();
        timeReady = date.getMinutes() + queueTime;
        orderList = new ArrayList<Order>();
    }

    public String getName() {
        return name;
    }

    public long getTimeLeft() {
        date = new Date();
        long timeLeft = timeReady - date.getMinutes();
        Log.i("Time", date.getMinutes()+"");
        Log.i("TimeReady", timeReady+"");
//        timeLeft = toMinute(timeLeft);
        if(timeLeft>=60)
            timeLeft-=60;
        return timeLeft;
    }

    public long toMinute(long timeLeft) {
        return timeLeft/60;
    }

    public void addOrder(Food food, int amount) {

        Order dummy = new Order(food,0);
        if(orderList.contains(dummy)) {

            Log.i("Index Of : ",orderList.indexOf(dummy)+"");
            orderList.set(orderList.indexOf(dummy), new Order(food, orderList.get(orderList.indexOf(dummy)).getAmount() + amount));

        }
        else
            orderList.add(new Order(food,amount));
    }

    public int getQueuePrice() {
        int totalPrice = 0;

        for(Order order : orderList) {
            if(!order.getStatus())
                totalPrice += order.getPrice();
        }
        return totalPrice;

    }


    public List<Order> getOrderList() {
        return orderList;
    }

    public void checkOrderList() {
        for(Order order : orderList) {
            order.check();
        }
    }
}
