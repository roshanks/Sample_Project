package com.notifications.app.tasklistapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.notifications.app.tasklistapp.R;
import com.notifications.app.tasklistapp.utils.AppContants;

/**
 * Created by Satish on 12/6/17.
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO start next screen
                Intent redirectToNextScreen=new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(redirectToNextScreen);
                finish();
            }
        }, AppContants.SPLASH_SCREEN_TIME);
    }
}
