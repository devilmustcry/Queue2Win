package com.sandstorm.softspec.queue2win.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FTTX on 4/12/2016 AD.
 */
public class Storage {

    private static Storage instance;

    private List<Customer> customerList;


    private Storage() {
        customerList = new ArrayList<Customer>();
        //test
//            customerList.add(new Customer("A","A"));
    }

    public static Storage getInstance() {
        if(instance == null)
            instance = new Storage();
        return instance;
    }

    public int findCustomer(String username,String password) {
        for(Customer customer : customerList) {
            if(customer.getUsername().equals(username)&&customer.getPassword().equals(password))
                return customerList.indexOf(customer);
        }
        return -1;
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }


}
