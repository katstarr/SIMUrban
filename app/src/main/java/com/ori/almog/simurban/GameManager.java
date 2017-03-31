package com.ori.almog.simurban;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.ori.almog.simurban.State.*;
import static com.ori.almog.simurban.State.MainMenu;

enum State {
    MainMenu,
    Options,
    GameScreen,
    SubsystemView
};

public class GameManager {

    private Context context;
    private AssetManager assetManager;
    private DrawManager drawManager;
    private State state = MainMenu;

    private HashMap<String, ViewBase> bases;

    public GameManager(Context context){

        this.context = context;
        this.assetManager = context.getAssets();
        this.bases = new HashMap<String, ViewBase>();
        this.init();
        this.drawManager = new DrawManager(this.bases.get("MainMenu"));
    }

    public DrawManager getDrawManager(){
        return this.drawManager;
    }

    public void begin() {

    }

    public void stop() {

    }

    public void handle(MotionEvent event){

        Point touch = new Point((int)event.getX(), (int)event.getY());
        Log.i("DEBUG", "Pressed: " + touch.toString());

        switch(this.state){
            case MainMenu:
                    for (int i = 0; i < this.drawManager.base.elements.size(); i++){
                        Rectangle r = this.drawManager.base.elements.get(i);
                        if (within(r.topLeft, r.bottomRight, touch)){
                            act(r.action);
                            break;
                        }
                    }

                break;

            case Options:

                break;

            case GameScreen:

                    for (int i = 0; i <this.drawManager.base.elements.size(); i++){
                        Rectangle r = this.drawManager.base.elements.get(i);
                        if (within(r.topLeft, r.bottomRight, touch)){
                            act(r.action);
                            break;
                        }
                    }

                break;

            case SubsystemView:

                break;
        }

        int size = 20;
        Rectangle x = this.drawManager.draws.get(0);
        x.topLeft.set((int)event.getX()-size, (int)event.getY()-size);
        x.bottomRight.set((int)event.getX()+size, (int)event.getY()+size);
    }

    private boolean within(Point topLeft, Point bottomRight, Point t){
        return t.x >= topLeft.x && t.x <= bottomRight.x && t.y >= topLeft.y && t.y <= bottomRight.y;
    }
    private void act(String action){
        //TODO: Consider switching on state here3.bn/

        switch(this.state){
            case MainMenu:
                switch (action){
                    case "start":
                        this.state = GameScreen;
                        this.drawManager.base = this.bases.get("GameScreen");
                        break;
                    case "options":
                        this.state = Options;
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    default:
                        Log.i("DEBUG", "UNKOWN TRANSITION");
                        Toast.makeText(context, "I don't know this button", Toast.LENGTH_LONG);
                }
                break;

            case GameScreen:
                switch (action){
                    case "back":
                        this.state = MainMenu;
                        this.drawManager.base =this.bases.get("MainMenu");
                        break;
                    default:
                        Toast.makeText(context, "ABC", Toast.LENGTH_LONG);
                }
                break;
        }
    }

    //TODO: Create a class for this...
    private void init(){

        Rect temp = new Rect();
        Typeface amatic = Typeface.createFromAsset(this.assetManager, "fonts/AmaticSC-Bold.ttf");

        //MAIN MENU
        ViewBase mainMenu = new ViewBase();
        mainMenu.bg.setColor(0xfffffaf2);
        mainMenu.elements.add(new Button(new Point(1080/2, 400), 0x00000000, 0xff513108, "Suburbia", 250, amatic, true, "title"));
        mainMenu.elements.add(new Button(new Point(1080/2, 1000), 0xff715128, 0xfffffaf2, "Start", 100, amatic, true, "start"));
        mainMenu.elements.add(new Button(new Point(1080/2, 1200), 0xff715128, 0xfffffaf2, "Options", 100, amatic, true, "options"));
        mainMenu.elements.add(new Button(new Point(1080/2, 1400), 0xff715128, 0xfffffaf2, "Exit", 100, amatic, true, "exit"));
        this.bases.put("MainMenu", mainMenu);

        //GAME SCREEN
        ViewBase gameScreen = new ViewBase();
        gameScreen.bg.setColor(0xfffffaf2);
        //gameScreen.elements.add(new Button(new Point(1080/2, 400), 0x00000000, 0xff513108, "A", 250, amatic, true, "A"));
        //gameScreen.elements.add(new Button(new Point(1080/2, 1000), 0xff715128, 0xfffffaf2, "B", 100, amatic, true, "B"));
        //gameScreen.elements.add(new Button(new Point(1080/2, 1200), 0xff715128, 0xfffffaf2, "C", 100, amatic, true, "C"));
        gameScreen.elements.add(new Button(new Point(0, 0), 0xff715128, 0xfffffaf2, "Back", 100, amatic, false, "back"));
        this.bases.put("GameScreen", gameScreen);
    }
}

class ViewBase {
    public ArrayList<Rectangle> elements;
    public Paint bg;

    public ViewBase(){
        this.elements = new ArrayList<Rectangle>();
        this.bg = new Paint();
        this.bg.setColor(0xff000000);
    };
}