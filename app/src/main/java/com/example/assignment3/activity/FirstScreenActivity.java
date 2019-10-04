package com.example.assignment3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.assignment3.R;

public class FirstScreenActivity extends AppCompatActivity {


    private static int Splash_Time_Out = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(FirstScreenActivity.this, HomeActivity.class);
                startActivity(intent);

                finish();
            }
        },Splash_Time_Out);
    }
}
