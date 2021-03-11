package com.abhi.cloneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashScreen extends AppCompatActivity {



    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent  = new Intent(splashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();  // must finish



                // i sec = 1000 mili sec
            }
        }, 3000);



    }






    }
