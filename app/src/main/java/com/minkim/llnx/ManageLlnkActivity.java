package com.minkim.llnx;

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
    public Button edit;
    public Button delete;

    /* Llnx */
    public ArrayList<Llnx> llnxes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_llnk);

        llnxList = (ListView) findViewById(R.id.llnxlist);
        edit     = (Button)   findViewById(R.id.edit);
        delete   = (Button)   findViewById(R.id.delete);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

    public void edit() {

    }

    public void delete() {

    }
}
