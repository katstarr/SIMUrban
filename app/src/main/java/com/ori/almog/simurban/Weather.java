package com.ori.almog.simurban;

import java.util.ArrayList;

public class Weather extends Subsystem {

    private int someVariable;
    private int myvalue;

    public Weather() {
        super();
        this.name = "Weather system";
    }

    @Override
    protected ArrayList<DrawableEntity> getDraws() {
        return null;
    }

    @Override
    protected void destination(Stimulus s) {

    }

    @Override
    protected void compute() {



    }
}
