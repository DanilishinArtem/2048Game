package com.example.a2048game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constants.dens = getResources().getDisplayMetrics().density;
        constants.sizeOfBlock = (int)(constants.dens * 80);
        setContentView(R.layout.activity_main);


    }
}