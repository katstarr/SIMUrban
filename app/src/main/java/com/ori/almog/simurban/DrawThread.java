package com.ori.almog.simurban;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class DrawThread extends Thread{

    private SurfaceHolder surfaceHolder;
    private DrawManager drawManager;

    private boolean running = false;

    public DrawThread(SurfaceHolder surfaceHolder, DrawManager drawManager){
        this.surfaceHolder = surfaceHolder;
        this.drawManager = drawManager;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    public boolean getRunning(){
        return this.running;
    }

    @Override
    public void run(){
        Canvas canvas;

        int i = 0;

        while(this.running){
            canvas = this.surfaceHolder.lockCanvas();
            if (canvas != null){
                //Log.i("DEBUG", "I am alive");
                this.drawManager.draw(canvas);
                this.surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
