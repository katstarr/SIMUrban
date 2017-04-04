package com.ori.almog.simurban;

import java.util.ArrayList;

public abstract class Subsystem extends Communicator {

    protected ArrayList<Stimulus> stimuli;

    public Subsystem(){
        this.stimuli = new ArrayList<>();
    }

    protected void destination(Stimulus s){
        this.stimuli.add(s);
    };
    abstract protected void compute();


}
