package com.example.a2048game;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    int currentScore, bestScore;
    private Bitmap block_empty, block_2, block_4, block_8, block_16, block_32, block_64, block_128, block_256,
    block_512, block_1024, block_2048;
    private int n;
    private int[][] table;
    private Resources resources;
    public Board(Resources resources, int n) {
        this.currentScore = 0;
        this.bestScore = 0;
        this.resources = resources;
        this.n = n;
        table = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                table[i][j] = 0;
            }
        }

        block_empty = BitmapFactory.decodeResource(resources, R.drawable.block_empty);
        block_2 = BitmapFactory.decodeResource(resources, R.drawable.block_2);
        block_4 = BitmapFactory.decodeResource(resources, R.drawable.block_4);
        block_8 = BitmapFactory.decodeResource(resources, R.drawable.block_8);
        block_16 = BitmapFactory.decodeResource(resources, R.drawable.block_16);
        block_32 = BitmapFactory.decodeResource(resources, R.drawable.block_32);
        block_64 = BitmapFactory.decodeResource(resources, R.drawable.block_64);
        block_128 = BitmapFactory.decodeResource(resources, R.drawable.block_128);
        block_256 = BitmapFactory.decodeResource(resources, R.drawable.block_256);
        block_512 = BitmapFactory.decodeResource(resources, R.drawable.block_512);
        block_1024 = BitmapFactory.decodeResource(resources, R.drawable.block_1024);
        block_2048 = BitmapFactory.decodeResource(resources, R.drawable.block_2048);

        block_empty = Bitmap.createScaledBitmap(block_empty, constants.sizeOfBlock, constants.sizeOfBlock, false);
        block_2 = Bitmap.createScaledBitmap(block_2, constants.sizeOfBlock, constants.sizeOfBlock, false);
        block_4 = Bitmap.createScaledBitmap(block_4, constants.sizeOfBlock, constants.sizeOfBlock, false);
        block_8 = Bitmap.createScaledBitmap(block_8, constants.sizeOfBlock, constants.sizeOfBlock, false);
        block_16 = Bitmap.createScaledBitmap(block_16, constants.sizeOfBlock, constants.sizeOfBlock, false);
        block_32 = Bitmap.createScaledBitmap(block_32, constants.sizeOfBlock, constants.sizeOfBlock, false);
        block_64 = Bitmap.createScaledBitmap(block_64, constants.sizeOfBlock, constants.sizeOfBlock, false);
        block_128 = Bitmap.createScaledBitmap(block_128, constants.sizeOfBlock, constants.sizeOfBlock, false);
        block_256 = Bitmap.createScaledBitmap(block_256, constants.sizeOfBlock, constants.sizeOfBlock, false);
        block_512 = Bitmap.createScaledBitmap(block_512, constants.sizeOfBlock, constants.sizeOfBlock, false);
        block_1024 = Bitmap.createScaledBitmap(block_1024, constants.sizeOfBlock, constants.sizeOfBlock, false);
        block_2048 = Bitmap.createScaledBitmap(block_2048, constants.sizeOfBlock, constants.sizeOfBlock, false);
        generateBlock();
        generateBlock();
    }
    void resetBoard(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                table[i][j] = 0;
            }
        }
        generateBlock();
        generateBlock();
    }
    void updateTable(int move){
        int counter = 0;
        boolean movement = false;
        int[][] startTable = new int[4][4];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                startTable[i][j] = table[i][j];
            }
        }
        if(move == 0){
            // вправо --->
            for(int i = 0; i < n; i++){
                for(int j = n - 1; j >= 0; j--){
                    if(table[i][j] != 0){
                        table[i][n - 1 - counter] = table[i][j];
                        if((n - 1 - counter) != j){
                            movement = true;
                            table[i][j] = 0;
                        }
                        counter++;
                    }
                }
                counter = 0;
            }
            for(int i = 0; i < n; i++){
                for(int j = n - 1; j > 0; j--){
                    if(table[i][j] == table[i][j-1]){
                        table[i][j] *= 2;
                        currentScore += table[i][j];
                        for(int k = j - 1; k > 0; k--){
                            table[i][k] = table[i][k-1];
                        }
                        table[i][0] = 0;
                    }
                }
            }
        }else if(move == 1){
            // влево
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(table[i][j] != 0){
                        table[i][counter] = table[i][j];
                        if(counter != j){
                            table[i][j] = 0;
                        }
                        counter++;
                    }
                }
                counter = 0;
            }
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n - 1; j++){
                    if(table[i][j] == table[i][j+1]){
                        table[i][j] *= 2;
                        currentScore += table[i][j];
                        for(int k = j + 1; k < n - 1; k++){
                            table[i][k] = table[i][k+1];
                        }
                        table[i][n-1] = 0;
                    }
                }
            }
        }else if(move == 2){
            // вниз
            for(int i = 0; i < n; i++){
                for(int j = n - 1; j >= 0; j--){
                    if(table[j][i] != 0){
                        table[n - 1 - counter][i] = table[j][i];
                        if((n - 1 - counter) != j){
                            table[j][i] = 0;
                        }
                        counter++;
                    }
                }
                counter = 0;
            }
            for(int i = 0; i < n; i++){
                for(int j = n - 1; j > 0; j--){
                    if(table[j][i] == table[j-1][i]){
                        table[j][i] *= 2;
                        currentScore += table[j][i];
                        for(int k = j - 1; k > 0; k--){
                            table[k][i] = table[k-1][i];
                        }
                        table[0][i] = 0;
                    }
                }
            }
        }else if(move == 3){
            // вверх
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(table[j][i] != 0){
                        table[counter][i] = table[j][i];
                        if(counter != j){
                            table[j][i] = 0;
                        }
                        counter++;
                    }
                }
                counter = 0;
            }
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n - 1; j++){
                    if(table[j][i] == table[j+1][i]){
                        table[j][i] *= 2;
                        currentScore += table[j][i];
                        for(int k = j + 1; k < n - 1; k++){
                            table[k][i] = table[k+1][i];
                        }
                        table[n-1][i] = 0;
                    }
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(startTable[i][j] != table[i][j]){
                    movement = true;
                }
            }
        }


        if(movement){
            generateBlock();
        }
    }
    boolean gameOver(){
        boolean GO = true;
        for(int i = 0; i < n; i++){
            if(table[0][i] != 0)
                GO = false;
        }
        /*
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(table[i][j] == 0){
                    GO = false;
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n - 1; j++){
                if(table[i][j] == table[i][j+1] || table[j][i] == table[j+1][i]){
                    GO = false;
                }
            }
        }
         */
        return GO;
    }
    public void generateBlock(){
        Random random = new Random();
        int x = random.nextInt(n);
        int y = random.nextInt(n);
        boolean check = true;
        while(check){
            if(table[x][y] > 0){
                x = random.nextInt(n);
                y = random.nextInt(n);
                check = true;
            }else{
                table[x][y] = 2;
                check = false;
            }
        }
    }
    public Bitmap getBlock_empty() {
        return block_empty;
    }

    public void setBlock_empty(Bitmap block_2) {
        this.block_empty = block_empty;
    }

    public Bitmap getBlock_2() {
        return block_2;
    }

    public void setBlock_2(Bitmap block_2) {
        this.block_2 = block_2;
    }

    public Bitmap getBlock_4() {
        return block_4;
    }

    public void setBlock_4(Bitmap block_4) {
        this.block_4 = block_4;
    }

    public Bitmap getBlock_8() {
        return block_8;
    }

    public void setBlock_8(Bitmap block_8) {
        this.block_8 = block_8;
    }

    public Bitmap getBlock_16() {
        return block_16;
    }

    public void setBlock_16(Bitmap block_16) {
        this.block_16 = block_16;
    }

    public Bitmap getBlock_32() {
        return block_32;
    }

    public void setBlock_32(Bitmap block_32) {
        this.block_32 = block_32;
    }

    public Bitmap getBlock_64() {
        return block_64;
    }

    public void setBlock_64(Bitmap block_64) {
        this.block_64 = block_64;
    }

    public Bitmap getBlock_128() {
        return block_128;
    }

    public void setBlock_128(Bitmap block_128) {
        this.block_128 = block_128;
    }

    public Bitmap getBlock_256() {
        return block_256;
    }

    public void setBlock_256(Bitmap block_256) {
        this.block_256 = block_256;
    }

    public Bitmap getBlock_512() {
        return block_512;
    }

    public void setBlock_512(Bitmap block_512) {
        this.block_512 = block_512;
    }

    public Bitmap getBlock_1024() {
        return block_1024;
    }

    public void setBlock_1024(Bitmap block_1024) {
        this.block_1024 = block_1024;
    }

    public Bitmap getBlock_2048() {
        return block_2048;
    }

    public void setBlock_2048(Bitmap block_2048) {
        this.block_2048 = block_2048;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[][] getTable() {
        return table;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }
}
