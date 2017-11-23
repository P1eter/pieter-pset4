package com.example.pieter.to_dolist;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;

/**
 * Created by pieter on 23-11-17.
 */

public class TodoAdapter extends ResourceCursorAdapter {
    public TodoAdapter(Context context, Cursor c) {
        super(context, R.layout.row_todo, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CheckBox cb = view.findViewById(R.id.checkbox);
        cb.setClickable(false);
        cb.setChecked(cursor.getInt(cursor.getColumnIndex("completed")) > 0);
        cb.setText(cursor.getString(cursor.getColumnIndex("title")));
    }
}
