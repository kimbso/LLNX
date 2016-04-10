package com.minkim.llnx;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.app.*;
import android.content.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;

import java.io.Serializable;

public class ManageLlnkActivity extends Activity implements View.OnClickListener, Serializable {

    /* Widgets */
    public ListView llnxList;
    public Button edit, delete, add;
    public SQLiteDatabase sampleDB;
    /* Llnx */
    public ArrayList<Llnx> llnxes = new ArrayList<>();

    public int ownerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_llnk);

        llnxList = (ListView) findViewById(R.id.llnxlist);
        edit     = (Button)   findViewById(R.id.edit);
        delete   = (Button)   findViewById(R.id.delete);
        add      = (Button)   findViewById(R.id.add);

        Intent myIntent = getIntent();
        Bundle myBundle = myIntent.getExtras();
        ownerId = myBundle.getInt("UserId");

        edit.setOnClickListener(this);
        delete.setOnClickListener(this);
        add.setOnClickListener(this);

        createDatabase();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                add();
                break;
            case R.id.edit:
                edit();
                break;
            case R.id.delete:
                delete();
                break;
        }
        for(Llnx i: llnxes)
            i.unset();
    }

    public void add(){
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.activity_add_llnk);
        dialog.setTitle("Custom Alert Dialog");

        ArrayList<String> tempNames = new ArrayList<>();
        tempNames.add("Michelle");
        tempNames.add("Joe");
        ArrayAdapter tempAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, tempNames);


        EditText editText   = (EditText) dialog.findViewById(R.id.beadnum);
        Spinner names       = (Spinner) dialog.findViewById(R.id.spinner);
        EditText nickname   = (EditText) dialog.findViewById(R.id.nickname);
        names.setAdapter(tempAdapter);

        dialog.show();

    }

    public void edit() {

    }

    public void delete() {

    }


//    DATABASE STUFF
//    public void insert(int beadNum, String name, String nickname){
//        String query = "Insert into LLNXTable(LLNXID, Owner, Recipient) values(" +
//                beadNum + ", " + ownerId +", ";
//    }

    public void createDatabase() {
        try {
            sampleDB = openOrCreateDatabase("NAME", MODE_PRIVATE, null);
            createLLNXTable();
            createPeopleTable();
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }
    }

    private void createLLNXTable() {
        String tableName = "LLNXTable";
        sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +
                " (LLNXID integer primary key autoincrement not null, " +
                "   Owner integer, " +
                "   Recipient integer);");
        Log.i("Created LLNX Table", "Done");
    }

    private void createPeopleTable(){
        String tableName = "PeopleTable";
        sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +
                " (PersonID integer primary key autoincrement not null, " +
                "   Name varchar);");
        Log.i("Created LLNX Table", "Done");

    }
}
