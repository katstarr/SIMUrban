package com.ori.almog.simurban;

import java.util.ArrayList;

class EMSRelay extends Subsystem {

    public EMSRelay(){
        super();
        this.name = "EMS Relay";
    }

    @Override
    protected ArrayList<DrawableEntity> getDraws() {
        return null;
    }

    @Override
    protected void destination(Stimulus s) {
        this.propagate(s);
    }

    @Override
    protected void compute() {

    }
}
