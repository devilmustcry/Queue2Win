package com.sandstorm.softspec.queue2win.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sandstorm.softspec.queue2win.Models.Storage;
import com.sandstorm.softspec.queue2win.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();

    }

    private void initComponents() {

        username = (EditText) findViewById(R.id.register_edittext_username);

        password = (EditText) findViewById(R.id.register_edittext_password);

        login = (Button) findViewById(R.id.register_button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")||password.getText().toString().equals(""))
                    Log.i("Dialog","Username or password not found");
                else {
                    int index = Storage.getInstance().findCustomer(username.getText().toString(),password.getText().toString());
                    if(index == -1)
                        Log.i("Dialog","Username or password incorrect");
                    else{
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("customerIndex", index);
                        Log.i("Dialog","Login Success");
                        startActivity(intent);
                    }

                }
            }
        });


        register = (Button) findViewById(R.id.register_button_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });



    }
}
