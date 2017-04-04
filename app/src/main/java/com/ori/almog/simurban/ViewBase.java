package com.ori.almog.simurban;

import android.graphics.Paint;

import java.util.ArrayList;

class ViewBase {
    public ArrayList<Rectangle> elements;
    public Paint bg;

    public ViewBase(){
        this.elements = new ArrayList<>();
        this.bg = new Paint();
        this.bg.setColor(0xff000000);
    };
}
