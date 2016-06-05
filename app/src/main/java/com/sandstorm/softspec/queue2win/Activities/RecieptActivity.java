package com.sandstorm.softspec.queue2win.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sandstorm.softspec.queue2win.Models.Order;
import com.sandstorm.softspec.queue2win.Models.Storage;
import com.sandstorm.softspec.queue2win.R;
import com.sandstorm.softspec.queue2win.Views.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;


public class RecieptActivity extends AppCompatActivity {


    private TextView checkout;
    private TextView balance;
    private TextView change;

    private EditText amount;
    private EditText password;

    private Button addBalance;
    private Button confirm;

    private List<Order> orderList;
    private ListView orderListView;
    private OrderListAdapter orderListAdapter;

    private int customerId;

    AlertDialog.Builder addBalanceDialog;
    AlertDialog.Builder passwordDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciept);
        initComponents();
    }

    private void initComponents() {

        checkout = (TextView) findViewById(R.id.reciept_text_checkout);
        balance = (TextView) findViewById(R.id.reciept_text_balance);
        change = (TextView) findViewById(R.id.reciept_text_change);

        addBalanceDialog = new AlertDialog.Builder(this);
        passwordDialog = new AlertDialog.Builder(this);

        orderListView = (ListView) findViewById(R.id.reciept_listview_foodlist);
        orderList = Storage.getInstance().getCustomerList().get(customerId).getQueue().getOrderList();
        orderListAdapter = new OrderListAdapter(this, R.layout.cell, orderList);
        orderListView.setAdapter(orderListAdapter);

        customerId = (int) getIntent().getSerializableExtra("customerId");

        setDetailText();


        addBalance = (Button) findViewById(R.id.reciept_button_addbalance);
        addBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setPasswordDialog();
            }
        });

        confirm = (Button) findViewById(R.id.reciept_button_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Storage.getInstance().getCustomerList().get(customerId).getBalance() >= Storage.getInstance().getCustomerList().get(customerId).getQueue().getQueuePrice()) {
                    Storage.getInstance().getCustomerList().get(customerId).withdrawal(Storage.getInstance().getCustomerList().get(customerId).getQueue().getQueuePrice());
                    Storage.getInstance().getCustomerList().get(customerId).getQueue().checkOrderList();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Insufficient Money", Toast.LENGTH_SHORT);
                }
            }
        });



    }

    private void setPasswordDialog() {

        password = new EditText(this);

        passwordDialog.setTitle("Input your password");
        passwordDialog.setView(password);
        passwordDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(password.getText().toString().equals(Storage.getInstance().getCustomerList().get(customerId).getPassword())) {
                    setAddBalanceDialog();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Password is wrong",Toast.LENGTH_SHORT);
                }
                }

        });
       passwordDialog.create().show();
    }

    private void setDetailText() {
        checkout.setText(Storage.getInstance().getCustomerList().get(customerId).getQueue().getQueuePrice()
                +"");

        balance.setText(Storage.getInstance().getCustomerList().get(customerId).getBalance()+"");

        change.setText((Storage.getInstance().getCustomerList().get(customerId).getBalance() - Storage.getInstance().getCustomerList().get(customerId).getQueue().getQueuePrice())+"");

    }

    private void setAddBalanceDialog() {
        amount = new EditText(this);
        amount.setInputType(InputType.TYPE_CLASS_NUMBER);

        addBalanceDialog.setTitle("How much do you want to add?");
        addBalanceDialog.setView(amount);
        addBalanceDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Storage.getInstance().getCustomerList().get(customerId).deposit(Integer.parseInt(amount.getText().toString()));
                setDetailText();

                Toast.makeText(getApplicationContext(), "Your balance have been added", Toast.LENGTH_SHORT);
            }
        });
        addBalanceDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog addBalanceDialogBuiled = addBalanceDialog.create();
        addBalanceDialogBuiled.show();


    }

    @Override
    protected void onStart() {
        super.onStart();

        orderList = Storage.getInstance().getCustomerList().get(customerId).getQueue().getOrderList();

        orderListAdapter.notifyDataSetChanged();
    }
}
