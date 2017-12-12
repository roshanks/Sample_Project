package com.notifications.app.tasklistapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.notifications.app.tasklistapp.Database.DBHandler;
import com.notifications.app.tasklistapp.R;
import com.notifications.app.tasklistapp.TaskModel.TaskModel;


/**
 * Created by roshan on 12/8/2017.
 */

public class AddTask extends AppCompatActivity implements View.OnClickListener {

    private EditText TaskName;
    private EditText TaskDescription;
    private Button TaskSave;
    private Button ViewTasks;

    private DBHandler db_object;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_register);
        init();
    }

    private void init() {

        db_object = new DBHandler(this);

        TaskName = findViewById(R.id.et_task_name);
        TaskDescription = findViewById(R.id.et_task_description);
        TaskSave = findViewById(R.id.bt_task);
        TaskSave.setOnClickListener(this);
        ViewTasks = findViewById(R.id.bt_viewtasks);
        ViewTasks.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String local_Task_Name = TaskName.getText().toString().trim();
        String local_Task_Description = TaskDescription.getText().toString().trim();
        TaskModel model = new TaskModel();

        switch (view.getId()) {
            case R.id.bt_task:

                db_object.openDatabase();
                long addTask = db_object.addTask(local_Task_Name, local_Task_Description);
                if (addTask != -1) {
                    Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    db_object.closeDatabase();
                } else {
                    Toast.makeText(this, "Not Saved, Please Try again", Toast.LENGTH_SHORT).show();
                    db_object.closeDatabase();
                }
                break;
            case R.id.bt_viewtasks:
                // Setting the Task Model
                Intent showTasks = new Intent(AddTask.this, validation_accepted.class);
                startActivity(showTasks);
                finish();

        }

    }
}