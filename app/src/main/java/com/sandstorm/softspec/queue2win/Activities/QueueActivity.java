package com.sandstorm.softspec.queue2win.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.sandstorm.softspec.queue2win.Models.Food;
import com.sandstorm.softspec.queue2win.Models.Order;
import com.sandstorm.softspec.queue2win.Models.Storage;
import com.sandstorm.softspec.queue2win.R;
import com.sandstorm.softspec.queue2win.Views.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueueActivity extends AppCompatActivity {

    private int customerId;


    private List<Order> orderList;
    private ListView orderListView;
    private OrderListAdapter orderListAdapter;



    private Button orderFoodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        initComponents();
    }

    private void initComponents() {
        customerId = (int) getIntent().getSerializableExtra("customerIndex");
        setTitle(Storage.getInstance().getCustomerList().get(customerId).getQueue().getName());

        orderList = Storage.getInstance().getCustomerList().get(customerId).getQueue().getOrderList();
        orderListView = (ListView) findViewById(R.id.queue_listview_orderlist);
        orderListAdapter = new OrderListAdapter(this, R.layout.cell, orderList);
        orderListView.setAdapter(orderListAdapter);



        orderFoodButton = (Button) findViewById(R.id.queue_button_orderfood);

        orderFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QueueActivity.this, OrderActivity.class);
                intent.putExtra("customerId",customerId);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        orderList = Storage.getInstance().getCustomerList().get(customerId).getQueue().getOrderList();


        orderListAdapter.notifyDataSetChanged();
    }
}
