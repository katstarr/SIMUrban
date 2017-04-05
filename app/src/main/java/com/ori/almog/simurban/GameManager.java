package com.ori.almog.simurban;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.Random;

import static com.ori.almog.simurban.State.*;
import static com.ori.almog.simurban.State.MainMenu;

public class GameManager {

    //The context of the main activity - needed to make toasts
    private Context context;

    //Draw manager is the class responsible for drawing things to the screen
    private DrawManager drawManager;

    //The state - where we are. see enum.
    private State state = MainMenu;

    //Asset manager is used to get pictures, fonts, etc.
    private AssetManager assetManager;

    //Provides basic UI elements for some screen
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
                Communicator centralRelay = new Communicator();
                EMSRelay emsRelay = new EMSRelay();


                //Create subsystems here
                Weather weather = new Weather();


                //Bind relays (lower -> upper)
                bind(centralRelay, rc);
                bind(emsRelay, centralRelay);

                //SUBSYSTEM BINDINGS
                bind(weather, centralRelay);
                //bind(church, centralRelay);
                //bind(mall, centralRelay);
                //bind(residences, centralRelay);
                //bind(school, centralRelay);

                //EMS BINDINGS
                //bind(fire, emsRelay);
                //bind(police, emsRelay);
                //bind(hospital, emsRelay);


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
                    drawManager.draws.add(new DrawableEntity(new Point(rand.nextInt(500), rand.nextInt(500)), new Point(500+rand.nextInt(500), 500+rand.nextInt(500)), rand.nextInt(), ""));
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
            DrawableEntity r = this.drawManager.base.elements.get(i);
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

    private void bind(Communicator lower, Communicator upper){
        lower.bindUpRelay(upper);
        upper.bindDownRelay(lower);
    }
}