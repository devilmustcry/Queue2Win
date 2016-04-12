package com.sandstorm.softspec.queue2win.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.sandstorm.softspec.queue2win.R;

public class LoginActivity extends AppCompatActivity {

    public EditText username;
    public EditText password;
    public Button login;
    public Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponents();

    }

    private void initComponents() {
        username = (EditText) findViewById(R.id.login_edittext_username);
        password = (EditText) findViewById(R.id.login_edittext_password);

        


    }
}
