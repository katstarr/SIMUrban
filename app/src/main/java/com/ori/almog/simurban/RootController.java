package com.ori.almog.simurban;

import java.util.HashMap;

class RootController extends Subsystem {

    private HashMap<String, HashMap<String, Change>> matrix;

    public RootController(){
        this.matrix = new HashMap<>();
        this.init();
    }

    @Override
    protected void destination(Stimulus s) {

        //Stimulus has arrived, figure out what to do with it

    }

    @Override
    protected void compute() {

        //Clock cycle, what do?

    }

    private void init(){

        //playground size increases enrollment with a multiplier of 1
        put("playGroundSize", "enrollment", new Change(Direction.Increases, 1.0));

        //playground size increases overall happiness with a multiplier of 1
        put("playGroundSize", "overallHappiness", new Change(Direction.Increases, 1.0));

        //mall size increases overallHappiness with multiplier of 2.0
        put("mallSize", "overallHappiness", new Change(Direction.Increases, 2.0));
        put("mallSize", "numberOfSassyTeens", new Change(Direction.Increases, 1.0));
        put("percentOfNoodles", "churchSize", new Change(Direction.Increases, 1.0));
        put("churchSize", "overallHappiness", new Change(Direction.Increases, 1.0));

        //TODO: Add the rest of them

    }

    private void put(String source, String affects, Change relationship){
        if (this.matrix.containsKey(source)){
            this.matrix.get(source).put(affects, relationship);
        }

        else {
            this.matrix.put(source, new HashMap<String, Change>());
            this.matrix.get(source).put(affects, relationship);
        }
    }
}