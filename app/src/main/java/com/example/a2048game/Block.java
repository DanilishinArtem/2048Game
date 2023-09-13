package com.example.a2048game;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class Block {
    private Bitmap block;
    private int x, y;
    private Rect rBlock;
    public Block(Bitmap block, int x, int y) {
        this.block = block;
        this.x = x;
        this.y = y;
    }

    public Bitmap getBlock() {
        return block;
    }

    public void setBlock(Bitmap block) {
        this.block = block;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rect getrBlock() {
        return new Rect(this.x, this.y, this.x + constants.sizeOfBlock, this.y + constants.sizeOfBlock);
    }
}
