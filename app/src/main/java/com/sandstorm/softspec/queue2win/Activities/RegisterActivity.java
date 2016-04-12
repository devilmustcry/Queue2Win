package com.sandstorm.softspec.queue2win.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sandstorm.softspec.queue2win.Models.Customer;
import com.sandstorm.softspec.queue2win.Models.Storage;
import com.sandstorm.softspec.queue2win.R;

import java.net.PasswordAuthentication;
import java.security.KeyStore;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private Button confirmButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponents();

    }

    private void initComponents() {
        username = (EditText) findViewById(R.id.register_edittext_username);

        password = (EditText) findViewById(R.id.register_edittext_password);

        confirmPassword = (EditText) findViewById(R.id.register_edittext_confirmpassword);

        confirmButton = (Button) findViewById(R.id.register_button_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isBlank()) {
                    if (confirm()) {
                        Storage.getInstance().addCustomer(new Customer(username.getText().toString(), password.getText().toString()));
                        Log.i("Dialog","Successfull register");
                        finish();
                    } else
                        Log.i("Dialog","This Username is already registered");

                } else
                    Log.i("Dialog", "Some textfield is blank");
            }
        });


        cancelButton = (Button) findViewById(R.id.register_button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean isBlank() {
        if(username.getText().toString().equals("")||password.getText().toString().equals("")||confirmPassword.getText().toString().equals(""))
            return true;
        return false;
    }

    private boolean confirm() {
        if(!password.getText().toString().equals(confirmPassword.getText().toString()))
            return false;
        for(Customer customer : Storage.getInstance().getCustomerList()){
            if(customer.getUsername().equals(username.getText().toString()))
                return false;
        }
        return true;
    }


}
