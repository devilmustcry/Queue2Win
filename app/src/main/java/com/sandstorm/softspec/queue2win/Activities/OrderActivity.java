package com.sandstorm.softspec.queue2win.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private Button getAllAmountButton;

    private TextView amount;

    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initComponents();
    }

    private void initComponents() {

        menus = Storage.getInstance().getFoodList();
        foodListAdapter = new FoodListAdapter(this, R.layout.food, menus);
        foodListView = (ListView) findViewById(R.id.order_listview_menu);

        foodListView.setAdapter(foodListAdapter);

        getAllAmountButton = (Button) findViewById(R.id.order_button_getallamount);
        getAllAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = 0;
                for(int i = 0;i<foodListView.getCount();i++){
                    v = foodListView.getChildAt(i);
                    amount = (TextView) v.findViewById(R.id.food_text_amount);
                    sum+= Integer.parseInt(amount.getText().toString());
                }
                Log.i("Sum", sum+"");
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
