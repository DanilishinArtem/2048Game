package com.example.a2048game;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class gameView extends View {
    TextView textViewScore, textViewBestScore, textViewGameOver;
    ImageButton resetButton;
    float startX = (float) 0, startY = (float) 0, endX, endY, deltaX, deltaY;
    private boolean move = false;
    Board board;
    private float mx, my;
    private android.os.Handler handler;
    private Runnable r;
    public gameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.board = new Board(getResources(), 4);
        handler = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }
    void setScore(TextView textViewScore, TextView textViewBestScore, ImageButton resetButton, TextView textViewGameOver){
        this.resetButton = resetButton;
        this.textViewScore = textViewScore;
        this.textViewBestScore = textViewBestScore;
        this.textViewGameOver = textViewGameOver;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int a = event.getActionMasked();
        if(MotionEvent.ACTION_DOWN == a){
            startX = event.getX();
            startY = event.getY();
        }else if(MotionEvent.ACTION_UP == a){
            endX = event.getX();
            endY = event.getY();
            deltaX = endX - startX;
            deltaY = endY - startY;
            if(deltaX > 0 && Math.max(Math.abs(deltaX), Math.abs(deltaY)) == Math.abs(deltaX) && Math.abs(deltaX) > constants.sizeOfBlock){
                // вправо
                board.updateTable(0);
            }else if(deltaX < 0 && Math.max(Math.abs(deltaX), Math.abs(deltaY)) == Math.abs(deltaX) && Math.abs(deltaX) > constants.sizeOfBlock){
                // влево
                board.updateTable(1);
            }else if(deltaY > 0 && Math.max(Math.abs(deltaX), Math.abs(deltaY)) == Math.abs(deltaY) && Math.abs(deltaY) > constants.sizeOfBlock){
                // вниз
                board.updateTable(2);
            }else if(deltaY < 0 && Math.max(Math.abs(deltaX), Math.abs(deltaY)) == Math.abs(deltaY) && Math.abs(deltaY) > constants.sizeOfBlock){
                // вверх
                board.updateTable(3);
            }
        }
        return true;
    }
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xFFA59B9B);
        for(int i = 0; i < board.getN(); i++){
            for(int j = 0; j < board.getN(); j++){
                if(board.getTable()[i][j] == 0){
                    canvas.drawBitmap(board.getBlock_empty(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 2){
                    canvas.drawBitmap(board.getBlock_2(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 4){
                    canvas.drawBitmap(board.getBlock_4(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 8){
                    canvas.drawBitmap(board.getBlock_8(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 16){
                    canvas.drawBitmap(board.getBlock_16(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 32){
                    canvas.drawBitmap(board.getBlock_32(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 64){
                    canvas.drawBitmap(board.getBlock_64(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 128){
                    canvas.drawBitmap(board.getBlock_128(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 256){
                    canvas.drawBitmap(board.getBlock_256(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 512){
                    canvas.drawBitmap(board.getBlock_512(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 1024){
                    canvas.drawBitmap(board.getBlock_1024(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 2048){
                    canvas.drawBitmap(board.getBlock_2048(), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), null);
                }
            }
        }
        if(board.gameOver()){
            textViewGameOver.setText("Game over");
            board.bestScore = Math.max(board.currentScore, board.bestScore);
            resetButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    board.resetBoard();
                }
            });
        }else{
            textViewGameOver.setText("");

        }
        textViewScore.setText("Очки:\n" + Integer.toString(board.currentScore));
        textViewBestScore.setText("Рекорд:\n" + Integer.toString(board.bestScore));
        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                board.currentScore = 0;
                board.resetBoard();
            }
        });
        handler.postDelayed(r, 100);
    }
}
