package com.ori.almog.simurban;

import java.util.ArrayList;
import java.util.HashMap;

class RootController extends Subsystem {

    private HashMap<String, HashMap<String, Change>> matrix;

    public RootController(){
        this.matrix = new HashMap<>();
        this.init();
    }

    @Override
    protected ArrayList<DrawableEntity> getDraws() {
        return null;
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

        /*
            VARIABLE LIST

                GLOBAL
                    -overallHappiness
                    -overallWealth
                    -overallHealth
                    -overallSpaghetti -> LOL
                    -population

                WEATHER
                    -cloudCover

                SCHOOL
                    -playgroundSize
                    -classroomCount

                CHURCH
                    -churchSize

                RESIDENCES


                MALL
                    -mallSize
                    -sassyTeenCount

                EMS-HOSPITAL
                    -medicineCount
                    -doctorCount
                    -averageERWait

                EMS-FIRE

                EMS-POLICE


         */

        /* WEATHER */
        put("cloudCover",           "overallHappiness",         new Change(Direction.Decreases, 1.0));
        put("cloudCover",           "sickPercent",              new Change(Direction.Increases, 1.0));


        /* SCHOOL */
        put("playgroundSize",       "averageERWait",            new Change(Direction.Increases, 1.0));




        /* CHURCH */
        put("percentOfNoodles",     "churchSize",               new Change(Direction.Increases, 1.0));
        put("churchSize",           "overallHappiness",         new Change(Direction.Increases, 1.0));


        /* RESIDENCES */




        /* MALL */
        put("mallSize",             "overallHappiness",         new Change(Direction.Increases, 2.0));
        put("mallSize",             "sassyTeenCount",           new Change(Direction.Increases, 1.0));


        /* EMS-HOSPITAL */
        put("medicineCount",        "overallHealth",            new Change(Direction.Increases, 1.0));
        put("doctorCount",          "overallHealth",            new Change(Direction.Increases, 1.5));
        put("doctorCount",          "averageERWait",            new Change(Direction.Decreases, 1.0));


        /* EMS-FIRE */




        /* EMS-POLICE */





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