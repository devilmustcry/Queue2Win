package com.sandstorm.softspec.queue2win.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandstorm.softspec.queue2win.Models.Order;
import com.sandstorm.softspec.queue2win.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by FTTX on 4/15/2016 AD.
 */
public class OrderListAdapter extends ArrayAdapter<Order> {

    public OrderListAdapter(Context context, int resource, List<Order> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null)
        {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cell,null);
        }

        Order order = getItem(position);

        TextView foodname = (TextView) v.findViewById(R.id.cell_text);
        foodname.setText(order.getFood().getName());

        TextView foodAmount = (TextView) v.findViewById(R.id.cell_amount);
        foodAmount.setText(order.getAmount()+"");

        TextView foodPrice = (TextView) v.findViewById(R.id.cell_price);
        foodPrice.setText(order.getPrice()+"");

        ImageView check = (ImageView) v.findViewById(R.id.cell_check);
        if(order.getStatus())
            check.setVisibility(View.VISIBLE);
        else
            check.setVisibility(View.INVISIBLE);

        return v;

    }
}
