package com.minkim.llnx;

import android.content.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import java.util.*;

public class LlnxAdapter extends ArrayAdapter implements Serializable {
    private final Context context;
    private final ArrayList<Llnx> tasks;

    public LlnxAdapter(Context context, ArrayList<Llnx> tasks) {
        super(context, R.layout.checkboxrow, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.checkboxrow, parent, false);
        final CheckBox cb = (CheckBox) rowView.findViewById(R.id.checkbox);
        cb.setText(tasks.get(position).getName());
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb.isChecked())
                    tasks.get(position).set();
                else if (!cb.isChecked())
                    tasks.get(position).unset();
            }
        });
        return rowView;
    }
}
