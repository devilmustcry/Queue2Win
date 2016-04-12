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
        
    }

    public Storage getInstance() {
        if(instance == null)
            instance = new Storage();
        return instance;
    }


}
