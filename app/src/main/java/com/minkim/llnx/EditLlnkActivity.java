package com.minkim.llnx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.app.*;
import android.content.*;

public class EditLlnkActivity extends AppCompatActivity implements View.OnClickListener {

    /* Widgets */
    public EditText username;
    public EditText nickname;
    public Button saveButton;
    public Button backButton;

    Intent myIntent;
    Bundle myBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_llnk);

        username   = (EditText) findViewById(R.id.username);
        nickname   = (EditText) findViewById(R.id.nickname);
        saveButton = (Button)   findViewById(R.id.saveButton);
        backButton = (Button)   findViewById(R.id.backButton);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
