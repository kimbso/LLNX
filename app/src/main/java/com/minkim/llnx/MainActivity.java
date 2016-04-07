package com.minkim.llnx;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.*;
import android.content.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button login, register, facebook;
    public boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login       = (Button) findViewById(R.id.login);
        register    = (Button) findViewById(R.id.register);
        facebook    = (Button) findViewById(R.id.facebook);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        facebook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.register:
                register();
                break;
            case R.id.facebook:
                facebook();
                break;
        }
    }

    /* Button Methods */

    public void login() {
        Intent loginActivity = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(loginActivity, 100);
        Log.i("onactivityresult", "here");
    }



    public void register() {

    }

    public void facebook() {

    }

    /* For the results */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 100) { // means that login has succeeded
            }
        } catch (Exception e) {
            Log.i("ERROR", "Issue in the onActivityResult");
        }

    }



}
