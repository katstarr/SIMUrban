package com.ori.almog.simurban;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingSurface extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread;
    private GameManager gameManager;

    public DrawingSurface(Context context, GameManager gameManager){
        super(context);

        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);

        this.gameManager = gameManager;
        this.drawThread = new DrawThread(this.surfaceHolder, this.gameManager.getDrawManager());
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        if (!this.drawThread.getRunning()){
            this.drawThread.setRunning(true);
            this.drawThread.start();
        }

        Log.i("DEBUG", "Starting game manager");
        this.gameManager.begin();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("DEBUG", "Stopping game manager");
        this.gameManager.stop();
    }
}
