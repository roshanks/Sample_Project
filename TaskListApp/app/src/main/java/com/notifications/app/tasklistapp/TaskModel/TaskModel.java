package com.notifications.app.tasklistapp.TaskModel;

/**
 * Created by roshan on 12/8/2017.
 */

public class TaskModel {
    private String TaskName;
    private String TaskDescription;

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        TaskDescription = taskDescription;
    }

    public String getTaskName() {
        return TaskName;
    }

    public String getTaskDescription() {
        return TaskDescription;
    }
}
