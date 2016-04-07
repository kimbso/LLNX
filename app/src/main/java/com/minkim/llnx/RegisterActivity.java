package com.minkim.llnx;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by roseanna on 4/7/16.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username, password, password2;
    Button register;
    SQLiteDatabase sampleDB = null;
    Cursor cursor;
    Intent myIntent;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username    = (EditText) findViewById(R.id.r_username);
        password    = (EditText) findViewById(R.id.r_password);
        password2   = (EditText) findViewById(R.id.r_password2);
        register    = (Button) findViewById(R.id.registerButton);

        register.setOnClickListener(this);
        createDatabase();
    }

    @Override
    public void onClick(View v) {
        String user = String.valueOf(username.getText());
        String pass = String.valueOf(password.getText());
        String pass2 = String.valueOf(password2.getText());
        Log.i("clicked", "x");
        boolean x = check(user, pass, pass2);
        if (x){
            Log.i("true", "registered");
            Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT);
            finish();
        }
    }

    public boolean check(String user, String pass, String pass2){
        Log.i("check", user);
        if (user.length() == 0 || pass.length() == 0 || pass2.length() == 0){
            toast = Toast.makeText(this, "Please fill in all inputs", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }

        if (!pass.equals(pass2)){
            toast = Toast.makeText(this, "Passwords dont match", Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
        String query = "Select * from loginTable where User = '" + user+"'";
        cursor = sampleDB.rawQuery(query, null);
        if (cursor.getCount() == 0){
            toast = Toast.makeText(this,"Registered", Toast.LENGTH_SHORT);
            toast.show();
            insertIntoDatabase(user, pass);
            cursor.close();
            return true;
        }
        else{
            toast = Toast.makeText(this, "Username has been taken", Toast.LENGTH_SHORT);
            toast.show();
            cursor.close();
            return false;
        }
    }

    //          CREATE DATABASE STUFF
    public void createDatabase(){
        Log.i("created database", "x");
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
    private void insertIntoDatabase (String user, String pass){
        String insertQuery = "Insert into loginTable(User, Password) values('" +
                user + "','" + pass + "')";
        sampleDB.execSQL(insertQuery);
    }
}
