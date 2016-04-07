package com.minkim.llnx;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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

    EditText username, password;
    Button login;
    SQLiteDatabase sampleDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.l_username);
        password = (EditText) findViewById(R.id.l_password);
        login = (Button) findViewById(R.id.loginButton);

        login.setOnClickListener(this);
        createDatabase();
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent();
        Bundle myBundle = new Bundle();
        String user = String.valueOf(username.getText());
        String pswd = String.valueOf(password.getText());
        if (check(user, pswd))
            myBundle.putString("LoggedIn", "true");
        else
            myBundle.putString("LoggedIn", "false");
        myIntent.putExtras(myBundle);
        setResult(Activity.RESULT_OK, myIntent);
        finish();
    }

    public boolean check(String user, String pass){
        String query = "Select * from loginTable " +
                "where User = '" + user + "' and Password = '" + pass + "';";
        Cursor cursor = sampleDB.rawQuery(query, null);
        Log.i("in check", "ss");
        if (cursor == null)
            return false;
        Log.i("check", "true");
        return true;
    }

    //          CREATE DATABASE STUFF
    public void createDatabase(){
        try{
            sampleDB = openOrCreateDatabase("NAME", MODE_PRIVATE, null);
            createLoginTable();
        }catch(SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }
    }
    private void createLoginTable() {
        String tableName = "loginTable";
        Log.i("Created Login Table", "Done");
        sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +
                " (UserID integer primary key autoincrement not null, " +
                "  User VARCHAR, " +
                "  Password VARCHAR);");
        Log.i("Created Login Table", "Done");
    }
}
