package com.notifications.app.tasklistapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.notifications.app.tasklistapp.Database.DBHandler;
import com.notifications.app.tasklistapp.TaskModel.TaskModel;
import com.notifications.app.tasklistapp.R;
import java.util.ArrayList;


/**
 * Created by roshan on 12/8/2017.
 */

public class AddTask extends AppCompatActivity implements View.OnClickListener {

    private EditText TaskName;
    private EditText TaskDescription;
    private Button TaskSave;
    private Button ViewTasks;
    private ListView listView;
    private DBHandler db_object;
    public ArrayList<TaskModel> dynamicTaskList = new ArrayList();

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
        listView = findViewById(R.id.lv_tasks);
        ArrayAdapter<String> TaskAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, dynamicTaskList);
       // listView.setAdapter(TaskAdapter);
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

                db_object.openDatabase();
                dynamicTaskList.clear();

                Cursor cursorobject = db_object.getAllTasks();

                while (cursorobject.moveToNext()) {
                    String TaskName = cursorobject.getString(cursorobject.getColumnIndex(db_object.CN_Task_name));
                    String TaskDescription = cursorobject.getString(cursorobject.getColumnIndex(db_object.CN_Task_description));

                    TaskModel t_model = new TaskModel();
                    t_model.setTaskName(TaskName);
                    t_model.setTaskDescription(TaskDescription);
                    dynamicTaskList.add(t_model);
                }
                db_object.closeDatabase();
                startActivity(showTasks);

        }

    }
}