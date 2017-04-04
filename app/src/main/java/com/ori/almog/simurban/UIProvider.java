package com.ori.almog.simurban;

import android.content.res.AssetManager;
import android.graphics.Point;
import android.graphics.Typeface;

import java.util.HashMap;

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
