package com.notifications.app.tasklistapp.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.notifications.app.tasklistapp.Database.DBHandler;
import com.notifications.app.tasklistapp.R;

import java.util.ArrayList;

/**
 * Created by rosha on 12/6/2017.
 */

public class validation_accepted extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton fab_task;
    private ListView listView;
    public ArrayList<String> dynamicTaskList = new ArrayList();
    private DBHandler db_object;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validation_confirmation);
        init();
    }

    private void init() {
        db_object = new DBHandler(this);
        fab_task = findViewById(R.id.fab_addtask);
        fab_task.setOnClickListener(this);

        listView = findViewById(R.id.lv_tasks);
        ArrayAdapter<String> TaskAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dynamicTaskList);
        if(listView!=null&&TaskAdapter!=null) {
            listView.setAdapter(TaskAdapter);
        }


        loadData();
    }

    private void loadData() {
        db_object.openDatabase();
        dynamicTaskList.clear();

        Cursor cursorobject = db_object.getAllTasks();

        if(cursorobject.moveToFirst()) {
            do{
                String TaskName = cursorobject.getString(cursorobject.getColumnIndex(db_object.CN_Task_name));
                String TaskDescription = cursorobject.getString(cursorobject.getColumnIndex(db_object.CN_Task_description));

                dynamicTaskList.add(TaskDescription);
            }while (cursorobject.moveToNext());
        }
        db_object.closeDatabase();

        ArrayAdapter<String> TaskAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dynamicTaskList);
        if(listView!=null&&TaskAdapter!=null) {
            listView.setAdapter(TaskAdapter);
        }

    }


    @Override
    public void onClick(View view) {

        Intent float_action_task = new Intent(validation_accepted.this,AddTask.class);
        startActivity(float_action_task);
        finish();
    }
}
