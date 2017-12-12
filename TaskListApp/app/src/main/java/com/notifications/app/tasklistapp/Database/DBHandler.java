package com.notifications.app.tasklistapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags;
import android.util.Log;

/**
 * Created by roshan on 12/8/2017.
 */

public class DBHandler {

    private Context context_local;
    private DBConnector TaskDBConnector;
    private SQLiteDatabase TaskDatabase;
    private String DB_ConnectionName = "DB_Task";

    public String TN_TaskID = "Task_Id";
    public String TN_Task = "Task";
    public String CN_Task_name = "Task_Name";
    public String CN_Task_description = "Task_Description";

    private String Create_Task = "create table "+ TN_Task+"("+ TN_TaskID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +CN_Task_name+" varchar(200),"+ CN_Task_description+" varchar(200))";

    private String Get_All_Task = "Select * from"+ TN_Task;


    public DBHandler(Context context){
        //context_local = context;
        TaskDBConnector = new DBConnector(context,DB_ConnectionName,null,1);
    }

    public void openDatabase(){
        TaskDatabase = TaskDBConnector.getWritableDatabase();
    }

    public void closeDatabase(){
        TaskDatabase.close();
    }

    public long addTask(String Name,String Description){
        ContentValues cv = new ContentValues();
        cv.put(CN_Task_name,Name);
        cv.put(CN_Task_description,Description);
        return TaskDatabase.insert(TN_Task,null,cv);
    }

    public Cursor getAllTasks(){
        Cursor cur = TaskDatabase.rawQuery(Get_All_Task,null);
        return cur;

    }

    class DBConnector extends SQLiteOpenHelper{

        public DBConnector(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);

        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(Create_Task);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
