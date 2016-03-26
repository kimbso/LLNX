package com.minkim.llnx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.*;
import android.content.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    String u = "fakeuser";
    String p = "fakepswd";
    EditText username, password;
    Button login;

    Intent myIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.l_username);
        password = (EditText) findViewById(R.id.l_password);
        login = (Button) findViewById(R.id.loginButton);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle myBundle = new Bundle();
        String user = String.valueOf(username.getText());
        String pswd = String.valueOf(password.getText());
        if (user.equals(u) && pswd.equals(p)) {
            username.setText("worked");
            myBundle.putString("login", "true");
            setResult(Activity.RESULT_OK, myIntent);
            finish();
        }
        else {
            username.setText("unauthorized");
            myBundle.putString("login", "true");
            setResult(Activity.RESULT_OK, myIntent);
            finish();
        }
    }
}
