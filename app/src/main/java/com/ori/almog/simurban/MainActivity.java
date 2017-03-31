package com.ori.almog.simurban;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {

    private DrawingSurface drawingSurface;
    private GameManager gameManager;
    private boolean pressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("DEBUG", "Creating main activity");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.gameManager = new GameManager(this);
        this.drawingSurface = new DrawingSurface(this, this.gameManager);

        setContentView(drawingSurface);
    }

    @Override
    protected void onResume() {
        Log.i("DEBUG", "Resumed!");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("DEBUG", "Paused!");
        super.onPause();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.pressed = true;
                this.gameManager.handle(event);
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:
                this.pressed = false;
                break;
        }

        return true;
    }

    @Override
    public void onBackPressed(){
        this.gameManager.back();
    }
}
