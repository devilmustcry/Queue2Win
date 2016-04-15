package com.sandstorm.softspec.queue2win.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.sandstorm.softspec.queue2win.Models.Food;
import com.sandstorm.softspec.queue2win.Models.Storage;
import com.sandstorm.softspec.queue2win.R;
import com.sandstorm.softspec.queue2win.Views.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

public class QueueActivity extends AppCompatActivity {

    private int customerId;
    private List<Food> orderList;
    private ListView orderListView;
    private OrderListAdapter orderListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        initComponents();
    }

    private void initComponents() {
        customerId = (int) getIntent().getSerializableExtra("customerIndex");
        setTitle(Storage.getInstance().getCustomerList().get(customerId).getQueue().getName());

        orderList = new ArrayList<Food>();
        orderListAdapter = new OrderListAdapter(this,R.layout.foodcell, orderList);

        orderListView = (ListView) findViewById(R.id.queue_listview_food);

        orderListView.setAdapter(orderListAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        orderList.clear();

        for(Food food : Storage.getInstance().getCustomerList().get(customerId).getQueue().getOrderList()) {
            orderList.add(food);
        }
        orderListAdapter.notifyDataSetChanged();
    }
}
