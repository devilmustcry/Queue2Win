package com.sandstorm.softspec.queue2win.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.sandstorm.softspec.queue2win.Models.Food;
import com.sandstorm.softspec.queue2win.Models.Storage;
import com.sandstorm.softspec.queue2win.R;
import com.sandstorm.softspec.queue2win.Views.FoodListAdapter;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private List<Food> menus;
    private FoodListAdapter foodListAdapter;
    private ListView foodListView;

    private int customerId;

    private Button orderButton;

    private TextView amount;

    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initComponents();
    }

    private void initComponents() {

        customerId = (int) getIntent().getSerializableExtra("customerId");

        menus = Storage.getInstance().getFoodList();
        foodListAdapter = new FoodListAdapter(this, R.layout.food, menus);
        foodListView = (ListView) findViewById(R.id.order_listview_menu);

        foodListView.setAdapter(foodListAdapter);

        orderButton = (Button) findViewById(R.id.order_button_order);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < foodListView.getCount(); i++) {
                    v = foodListView.getChildAt(i);
                    amount = (TextView) v.findViewById(R.id.food_text_amount);
                    if (Integer.parseInt(amount.getText().toString()) != 0) {
                        Storage.getInstance()
                                .getCustomerList()
                                .get(customerId)
                                .getQueue()
                                .addOrder(Storage.getInstance()
                                        .getFoodList()
                                        .get(i), Integer.parseInt(amount.getText().toString()));
                    }
                }
                finish();
                //Instead of finish it should go to reciept but for the sake of testing...
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();

        menus = Storage.getInstance().getFoodList();

        foodListAdapter.notifyDataSetChanged();
    }
}
