package com.ori.almog.simurban;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.provider.Contacts;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.RunnableFuture;

import static com.ori.almog.simurban.State.*;
import static com.ori.almog.simurban.State.MainMenu;

enum State {
    MainMenu,
    Options,
    GameScreen,
    SubsystemView
};

public class GameManager {

    //The context of the main activity - needed to make toasts
    private Context context;

    //Draw manager is the class responsible for drawing things to the screen
    private DrawManager drawManager;

    //The state - where we are. see enum.
    private State state = MainMenu;

    //Asset manager is used to get pictures, fonts, etc.
    private AssetManager assetManager;

    //Provides basic UI elements for soem screen
    private UIProvider uiProvider;

    private boolean running = false;

    private Random rand;



    public GameManager(Context context){
        this.context = context;
        this.assetManager = context.getAssets();
        this.rand = new Random();

        this.uiProvider = new UIProvider(this.assetManager);
        this.drawManager = new DrawManager(this.uiProvider.get("MainMenu"));
    }

    public DrawManager getDrawManager(){
        return this.drawManager;
    }

    //This method should unpause the simulation
    public void begin() {
        this.running = true;

        Runnable game = new Runnable(){
            public void run(){


                //Create root controller here
                RootController rc = new RootController();

                //Create relays here


                //Create subsystems here



                //Bind relays


                //Enter game loop (each loop = one day)
                while (running){


                    //Get stimuli from buffer



                    //Respond internally



                    //Upload to relay



                    //Root controller marix work


                    //Create Delta instance


                    //Propagate delta



                    //Finalize




                    //Just for fun (remove this later)
                    drawManager.draws.add(new Rectangle(new Point(rand.nextInt(500), rand.nextInt(500)), new Point(500+rand.nextInt(500), 500+rand.nextInt(500)), rand.nextInt(), ""));
                    Log.i("DEBUG", "Made a rect");

                    //Sleep for 1 second (indicate new day..?)
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t = new Thread(game);
        t.start();
    }

    //This method should pause the simulation
    public void stop() {
        this.running = false;
    }

    //Back button pressed...
    public void back(){
        switch(this.state){
            case GameScreen:
                this.stop();
                this.drawManager.base = this.uiProvider.get("MainMenu");
                this.drawManager.resetDraws();
                this.state = MainMenu;
                break;
            case Options:
                this.drawManager.base = this.uiProvider.get("MainMenu");
                this.state = MainMenu;
                break;
            case MainMenu:
                System.exit(0);
                break;
        }
    }

    public void handle(MotionEvent event){

        Point touch = new Point((int)event.getX(), (int)event.getY());
        Log.i("DEBUG", "Pressed: " + touch.toString());

        for (int i = 0; i < this.drawManager.base.elements.size(); i++){
            Rectangle r = this.drawManager.base.elements.get(i);
            if (within(r.topLeft, r.bottomRight, touch)){
                press(r.action);
                break;
            }
        }

        //update cursor
        int size = 20;
        this.drawManager.cursor.topLeft.set((int)event.getX()-size, (int)event.getY()-size);
        this.drawManager.cursor.bottomRight.set((int)event.getX()+size, (int)event.getY()+size);
    }

    //Returns true if the point t is within a rectangle inscribed by topLeft and bottomRight
    private boolean within(Point topLeft, Point bottomRight, Point t){
        return t.x >= topLeft.x && t.x <= bottomRight.x && t.y >= topLeft.y && t.y <= bottomRight.y;
    }

    private void press(String action){

        switch(this.state){
            case MainMenu:
                switch (action){
                    case "start":
                        this.state = GameScreen;
                        this.drawManager.base = this.uiProvider.get("GameScreen");
                        this.begin();
                        break;
                    case "options":
                        this.state = Options;
                        this.drawManager.base = this.uiProvider.get("OptionsScreen");
                        break;
                    case "exit":
                        System.exit(0);
                        break;
                    default:
                        Log.i("DEBUG", "UNKNOWN TRANSITION");
                        Toast.makeText(context, "I don't know this button", Toast.LENGTH_SHORT).show();
                }
                break;

            case GameScreen:

                break;

            case Options:
                break;
        }
    }
}

class UIProvider {

    //Bases are basic UI components attributed to a view by a string. They are defined in the init() method of this class.
    private HashMap<String, ViewBase> bases;

    public UIProvider(AssetManager assetManager){
        this.bases = new HashMap<>();
        this.init(assetManager);
    }

    public ViewBase get(String view){
        return this.bases.get(view);
    }

    //Defines UI elements for various screens
    private void init(AssetManager assetManager){

        Typeface amatic = Typeface.createFromAsset(assetManager, "fonts/AmaticSC-Bold.ttf");

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
        //gameScreen.elements.add(new Button(new Point(10, 10), 0xff715128, 0xfffffaf2, "Back", 100, amatic, false, "back"));
        this.bases.put("GameScreen", gameScreen);

        //OPTIONS
        ViewBase optionsScreen = new ViewBase();
        optionsScreen.bg.setColor(0xfffffaf2);
        optionsScreen.elements.add(new Button(new Point(1080/2, 400), 0x00000000, 0xff513108, "Options", 250, amatic, true, "title"));
        //optionsScreen.elements.add(new Button(new Point(10, 10), 0xff715128, 0xfffffaf2, "Back", 100, amatic, false, "back"));
        this.bases.put("OptionsScreen", optionsScreen);
    }
}

class ViewBase {
    public ArrayList<Rectangle> elements;
    public Paint bg;

    public ViewBase(){
        this.elements = new ArrayList<>();
        this.bg = new Paint();
        this.bg.setColor(0xff000000);
    };
}