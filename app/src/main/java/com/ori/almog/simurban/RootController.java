package com.ori.almog.simurban;

import java.util.HashMap;

public class RootController extends Subsystem {

    private HashMap<String, HashMap<String, Change>> matrix;

    public RootController(){
        this.matrix = new HashMap<>();
        this.init();
    }

    @Override
    protected void compute() {

    }

    private void init(){

        //playground size increases enrollment with a multiplier of 1
        put("playGroundSize", "enrollment", new Change(Direction.Increases, 1.0));

        //playground size increases overall happiness with a multiplier of 1
        put("playGroundSize", "overallHappiness", new Change(Direction.Increases, 1.0));
//mall size increases overallHappiness with mutipler of 2.0
        put("mallSize","overallHappiness", new Change(Direction.Increases,2.0));
        put("mallSize","numberofSassyTeens", new Change(Direction.Increases,1.0));
        put("percentofNoodles","churchSize",new Change(Direction.Increases,1.0));
        put("churchSize","overallHappiness",new Change(Direction.Increases,1.0));



















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