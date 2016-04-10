package com.minkim.llnx;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddFriendActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username;
    Button addFriend;
    SQLiteDatabase sampleDB;
    ArrayList<Person> peopleArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        username    = (EditText) findViewById(R.id.username);
        addFriend   = (Button) findViewById(R.id.addFriend);
        peopleArray = new ArrayList<>();

        addFriend.setOnClickListener(this);
        createDatabase();
    }

    public void onClick(View v){
        String user = username.getText().toString();
        insert(user);
    }

    public void createDatabase() {
        try {
            sampleDB = openOrCreateDatabase("NAME", MODE_PRIVATE, null);
            createPeopleTable();
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }
    }

    private void createPeopleTable() {
        String tableName = "PeopleTable";
        sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +
                "   (PersonID integer primary key autoincrement not null, " +
                "   Name varchar);");
        Log.i("Created People Table", "Done");
    }
    private boolean check(String username){
        String query = "Select * from peopleTable " +
                "where Name = '" + username + "';";
        Cursor cursor = sampleDB.rawQuery(query, null);
        if (cursor.getCount() == 0)
            return true;
        Log.i("check person", "true");
        return false;

    }

    private void insert(String username){
        if (check(username)){
            String query = "Insert into PeopleTable(Name) values ('" +
                    username + "');";
            sampleDB.execSQL(query);
            Toast.makeText(this, "Added Friend", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Unable to add", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateAL(){
        peopleArray.clear();
        String query = "Select * from PeopleTable";
        Cursor cursor = sampleDB.rawQuery(query, null);
        if (cursor != null){
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String user = cursor.getString(1);
                Person newPerson = new Person(id, user);
                peopleArray.add(newPerson);
            }
        }

    }
}
