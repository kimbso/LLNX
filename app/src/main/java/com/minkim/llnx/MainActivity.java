package com.minkim.llnx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.*;
import android.util.*;
import android.widget.*;
import android.database.sqlite.*;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button login, register, facebook;
    public boolean loggedIn = false;
    SQLiteDatabase sampleDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        facebook = (Button) findViewById(R.id.facebook);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        facebook.setOnClickListener(this);
        createDatabase();
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
        Intent registerActivity = new Intent(MainActivity.this, RegisterActivity.class);
        startActivityForResult(registerActivity, 100);
        Log.i("onactivityresult", "here");
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

    public void createDatabase() {
        try {
            sampleDB = openOrCreateDatabase("NAME", MODE_PRIVATE, null);
            createLoginTable();
            createLLNXTable();
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }
    }

    private void createLoginTable() {
        String tableName = "loginTable";
        sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +
                " (UserID integer primary key autoincrement not null, " +
                "  User VARCHAR, " +
                "  Password VARCHAR);");
        Log.i("Created Login Table", "Done");
    }

    private void createLLNXTable(){
        String tableName = "LLNXTable";
        sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +
                " (LLNXID integer primary key autoincrement not null, " +
                "   Owner integer, " +
                "   Recipient integer);");
        Log.i("Created LLNX Table", "Done");
    }
}