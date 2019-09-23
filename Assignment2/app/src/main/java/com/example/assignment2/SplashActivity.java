package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private TextView mtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    initUi();

    }

    private void initUi() {
        mtext=findViewById(R.id.tv_ass);
        splashScreen();
    }

    private void splashScreen() {
        Thread thread=new Thread(){
            public void run(){
                try{
                    sleep(2000);
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();

                }catch (Exception e){}

            }
        };thread.start();
    }
}
