package com.example.a2048game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class gameView extends View {
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
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int a = event.getActionMasked();
        switch (a){
            case MotionEvent.ACTION_MOVE:{
                if(move == false){
                    mx = event.getX();
                    my = event.getY();
                    move = true;
                }else{
                    if(mx - event.getX() > constants.sizeOfBlock){
                        mx = event.getX();
                        my = event.getY();
                        //snake.setMove_left(true);
                        board.updateTable();
                        break;
                    }else if(event.getX() - mx > constants.sizeOfBlock){
                        mx = event.getX();
                        my = event.getY();
                        //nake.setMove_right(true);
                        board.updateTable();
                        break;
                    }else if(my - event.getY() > constants.sizeOfBlock){
                        mx = event.getX();
                        my = event.getY();
                        //snake.setMove_top(true);
                        board.updateTable();
                        break;
                    }else if(event.getY() - my > constants.sizeOfBlock){
                        mx = event.getX();
                        my = event.getY();
                        //snake.setMove_bottom(true);
                        board.updateTable();
                        break;
                    }
                }
                // break;
            }
            case MotionEvent.ACTION_UP:{
                mx = 0;
                my = 0;
                move = false;
                break;
            }
        }
        return true;
    }
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xFFA59B9B);
        // board.updateTable();
        for(int i = 0; i < board.getN(); i++){
            for(int j = 0; j < board.getN(); j++){
                if(board.getTable()[i][j] == 0){
                    canvas.drawBitmap(board.getBlock_empty(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 2){
                    canvas.drawBitmap(board.getBlock_2(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 4){
                    canvas.drawBitmap(board.getBlock_4(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 8){
                    canvas.drawBitmap(board.getBlock_8(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 16){
                    canvas.drawBitmap(board.getBlock_16(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 32){
                    canvas.drawBitmap(board.getBlock_32(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 64){
                    canvas.drawBitmap(board.getBlock_64(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 128){
                    canvas.drawBitmap(board.getBlock_128(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 256){
                    canvas.drawBitmap(board.getBlock_256(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 512){
                    canvas.drawBitmap(board.getBlock_512(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 1024){
                    canvas.drawBitmap(board.getBlock_1024(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }else if(board.getTable()[i][j] == 2048){
                    canvas.drawBitmap(board.getBlock_2048(), (int)(constants.dens * i * (80 + 10)) + (int)(10 * constants.dens), (int)(constants.dens * j * (80 + 10)) + (int)(10 * constants.dens), null);
                }
            }
        }
        handler.postDelayed(r, 100);
    }
}
