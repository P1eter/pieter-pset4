package com.example.pieter.to_dolist;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TodoDatabase db;
    TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = TodoDatabase.getInstance(getApplicationContext());

        ListView todoview = findViewById(R.id.todolistview);

        Cursor all_data = db.selectAll();
        adapter = new TodoAdapter(this, all_data);

        todoview.setAdapter(adapter);
        todoview.setOnItemClickListener(new listviewclickhandler());
        todoview.setOnItemLongClickListener(new listviewlongclickhandler());
    }

    public void addItem(View view) {
        TextView tv = findViewById(R.id.tv);
        TodoDatabase.getInstance(getApplicationContext()).insert(tv.getText().toString(), 0);
        tv.setText("");
        updateData();
    }

    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

    private class listviewclickhandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            CheckBox cb = view.findViewById(R.id.checkbox);
            cb.setChecked(!cb.isChecked());

            db.update(l, (cb.isChecked() ? 1 : 0));

            updateData();
        }
    }

    private class listviewlongclickhandler implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            db.delete(l);
            updateData();
            return true;
        }
    }
}
