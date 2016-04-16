package com.sandstorm.softspec.queue2win.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.sandstorm.softspec.queue2win.Models.Food;
import com.sandstorm.softspec.queue2win.R;

import java.util.List;

/**
 * Created by FTTX on 4/16/2016 AD.
 */
public class FoodListAdapter extends ArrayAdapter<Food> {


    public FoodListAdapter(Context context, int resource, List<Food> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null)
        {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.food, null);
        }

        Food food = getItem(position);

        TextView name = (TextView) v.findViewById(R.id.food_text_name);

        name.setText(food.getName());

        final TextView amount = (TextView) v.findViewById(R.id.food_text_amount);

        Button minusButton = (Button) v.findViewById(R.id.food_button_minus);

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(amount.getText().toString())!=0)
                    amount.setText((Integer.parseInt(amount.getText().toString())-1)+"");
            }
        });


        Button plusButton = (Button) v.findViewById(R.id.food_button_plus);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText((Integer.parseInt(amount.getText().toString())+1)+"");
            }
        });



        return v;

    }

}
