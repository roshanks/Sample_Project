package com.notifications.app.tasklistapp.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.notifications.app.tasklistapp.R;
import com.notifications.app.tasklistapp.utils.AppContants;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Satish on 12/6/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText loginid;
    private TextInputEditText loginpwd;
    private Button loginbutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        loginid = findViewById(R.id.tiet_login_id);
        loginpwd = findViewById(R.id.tiet_login_pwd);
        loginbutton = findViewById(R.id.bt_login);
        loginbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String Login = loginid.getText().toString();
        String Passwd = loginpwd.getText().toString();
        int Username_length = Login.length();
        int Password_length = Passwd.length();
        Boolean noBlankSpace = false;

        /* Username Validation */
        int i = 0;
        while(Login.charAt(i)!=' ') {
            if (i < Username_length-1) {
                i++;
            }else{
                break;
            }
        }
        if(i == Username_length-1)
        {
            noBlankSpace = true;
        }
        if(loginid.getText().toString()!= null && noBlankSpace) {
              /* Password Validation */
            if(Password_length>=8){
                Pattern letter = Pattern.compile("[a-zA-Z]");
                Pattern digit = Pattern.compile("[0-9]");
                Pattern special_char = Pattern.compile("[[!@#$%&*()_+=|<>?{}\\\\[\\\\]~-]]");
                Matcher isletter = letter.matcher(Passwd);
                Matcher isdigit = letter.matcher(Passwd);
                Matcher isspecial_char = letter.matcher(Passwd);
                if(isletter.find()&&isdigit.find()&&isspecial_char.find()){
                    Intent validation_screen = new Intent(LoginActivity.this, SplashScreenActivity.class);
                    startActivity(validation_screen);
                }
            }else{
                Toast.makeText(this, "Passwords are alpha_numeric with atleast 'one special character and 8 characters long'", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Blankspaces are not allowed in Usernames", Toast.LENGTH_SHORT).show();
        }



    }



//    public void validation(View v){
//        Toast.makeText(this, "Entered", Toast.LENGTH_SHORT).show();
//        if(loginid.getText()!= null){
//
//            Intent validation_screen = new Intent(LoginActivity.this,validation_accepted.class);
//            startActivity(validation_screen);
//        }
//
//    }
}
