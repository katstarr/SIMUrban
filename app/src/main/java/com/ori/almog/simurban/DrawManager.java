package com.ori.almog.simurban;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Log;

import java.util.ArrayList;

/*
Class responsibilities:
    - Keep track of objects to be drawn on screen
    - Not call to draw elements not on screen
    - Interface with drawing thread
 */
class DrawManager{

    private Paint paint;
    private Paint BLACK;

    public ViewBase base;
    public ArrayList<DrawableEntity> draws;

    public DrawableEntity cursor;

    public DrawManager(ViewBase init){

        this.base = init;
        this.resetDraws();

        this.paint = new Paint();
        this.paint.setColor(0xffff0000);

        this.BLACK = new Paint();
        this.BLACK.setColor(0xff000000);
        this.BLACK.setTextSize(100);

        this.cursor = new DrawableEntity(new Point(0,0), new Point(100,100), 0xffff0000, "");
    }

    public void resetDraws(){
        this.draws = new ArrayList<>();
    }

    public void draw(Canvas canvas) {

        int x = canvas.getWidth();
        int y = canvas.getHeight();

        //Clear background
        canvas.drawRect(0, 0, x, y, this.base.bg);

        for (int i = 0; i < this.base.elements.size(); i++){
            DrawableEntity e = this.base.elements.get(i);
            drawEntity(e, canvas);
        }

        for (int i = 0; i < this.draws.size(); i++){
            DrawableEntity e = this.draws.get(i);
            drawEntity(e, canvas);
        }

        this.drawEntity(this.cursor, canvas);
    }

    private void drawEntity(DrawableEntity e, Canvas canvas){
        if (e instanceof Button) {
            //canvas.drawRect(e.topleft.x, e.topleft.y, e.bottomRight.x, e.bottomRight.y, e.back);
            canvas.drawRoundRect(e.topLeft.x, e.topLeft.y, e.bottomRight.x, e.bottomRight.y, 25.0f, 25.0f, e.back);
            canvas.drawText(((Button) e).text, 0, ((Button) e).text.length(), ((Button) e).textSource.x, ((Button) e).textSource.y, ((Button) e).font);
        }

        else if (e instanceof DrawableEntity){
            canvas.drawRect(e.topLeft.x, e.topLeft.y, e.bottomRight.x, e.bottomRight.y, e.back);
        }
    }
}

class DrawableEntity {

    public Point topLeft;
    public Point bottomRight;
    public Paint back;
    public String action = "";

    public DrawableEntity(Point topleft, Point bottomRight, int col, String action){
        this.topLeft = topleft;
        this.bottomRight = bottomRight;

        this.back = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.back.setColor(col);

        this.action = action;
    }
}

class Button extends DrawableEntity {
    public String text;
    public Paint font;
    public Point textSource;

    public Button(Point p, int backCol, int textCol, String text, int textSize, Typeface typeface, boolean center, String action){

        super(null, null, backCol, action);

        this.font = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.font.setColor(textCol);
        this.font.setTextSize(textSize);
        this.font.setTypeface(typeface);

        this.text = text;

        Paint.FontMetrics fm = new Paint.FontMetrics();
        font.setTextAlign(Paint.Align.CENTER);
        font.getFontMetrics(fm);

        float length = font.measureText(this.text, 0, this.text.length()) * 1.15f;
        float height = (fm.descent - fm.ascent);

        Log.i("DEBUG", "Length: " + String.valueOf(length));
        Log.i("DEBUG", "Height: " + String.valueOf(height));

        if (center){
            this.topLeft = new Point((int) (p.x - length / 2), (int) (p.y + fm.ascent));
            this.bottomRight = new Point((int) (p.x + length / 2), (int) (p.y + fm.descent));
            this.textSource = p;
        } else {
            this.topLeft = new Point(p.x, p.y);
            this.bottomRight = new Point((int)(p.x + length), (int) (p.y + height));
            this.textSource = new Point((int) (p.x + length / 2), (int) (p.y - fm.ascent));
        }
    }
}