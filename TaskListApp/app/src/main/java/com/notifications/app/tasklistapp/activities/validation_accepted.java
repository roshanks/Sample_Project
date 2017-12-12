package com.notifications.app.tasklistapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Toast;


import com.notifications.app.tasklistapp.R;

/**
 * Created by rosha on 12/6/2017.
 */

public class validation_accepted extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton fab_task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validation_confirmation);
        init();
    }

    private void init() {
        fab_task = findViewById(R.id.fab_addtask);
        fab_task.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        Intent float_action_task = new Intent(validation_accepted.this,AddTask.class);
        startActivity(float_action_task);

    }
}
