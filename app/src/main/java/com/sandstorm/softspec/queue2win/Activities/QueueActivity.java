package com.sandstorm.softspec.queue2win.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.sandstorm.softspec.queue2win.Models.Food;
import com.sandstorm.softspec.queue2win.Models.Storage;
import com.sandstorm.softspec.queue2win.R;
import com.sandstorm.softspec.queue2win.Views.AmountListAdapter;
import com.sandstorm.softspec.queue2win.Views.FoodListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueueActivity extends AppCompatActivity {

    private int customerId;



    private List<Food> orderList;
    private ListView orderListView;
    private FoodListAdapter foodListAdapter;


    private List<Integer> amountList;
    private ListView amountListView;
    private AmountListAdapter amountListAdapter;



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

        orderList = new ArrayList<Food>();
        foodListAdapter = new FoodListAdapter(this,R.layout.cell, orderList);

        orderListView = (ListView) findViewById(R.id.queue_listview_food);

        orderListView.setAdapter(foodListAdapter);

        amountList = new ArrayList<Integer>();
        amountListView = (ListView) findViewById(R.id.queue_listview_amount);
        amountListAdapter = new AmountListAdapter(this,R.layout.cell,amountList);

        amountListView.setAdapter(amountListAdapter);




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

        orderList.clear();
        amountList.clear();

        Map<Food, Integer> orders = Storage.getInstance().getCustomerList().get(customerId).getQueue().getOrderList();


        for(Food food : orders.keySet()) {
            orderList.add(food);
        }

        for(Food food : orders.keySet()) {
            amountList.add(orders.get(food));
            Log.i("Amount", orders.get(food)+"");
        }

        foodListAdapter.notifyDataSetChanged();
        amountListAdapter.notifyDataSetChanged();
    }
}
