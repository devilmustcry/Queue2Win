package com.sandstorm.softspec.queue2win.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sandstorm.softspec.queue2win.R;

import java.util.List;

/**
 * Created by FTTX on 4/15/2016 AD.
 */
public class AmountListAdapter extends ArrayAdapter<Integer> {


    public AmountListAdapter(Context context, int resource, List<Integer> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.cell, null);
        }

        TextView foodAmount = (TextView) v.findViewById(R.id.cell_text);

        Integer amount = getItem(position);

        foodAmount.setText(amount+"");
        return v;
    }
}
