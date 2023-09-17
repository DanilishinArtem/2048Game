package com.example.a2048game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    gameView Gameview;
    ImageButton resetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constants.dens = getResources().getDisplayMetrics().density;
        constants.sizeOfBlock = (int)(constants.dens * 80);
        setContentView(R.layout.activity_main);
        Gameview = findViewById(R.id.gv);
        TextView score, bestScore, textViewGameOver;
        resetButton = findViewById(R.id.reset);
        score = findViewById(R.id.score);
        bestScore = findViewById(R.id.bestScore);
        textViewGameOver = findViewById(R.id.gameOver);
        Gameview.setScore(score, bestScore, resetButton, textViewGameOver);

    }
}