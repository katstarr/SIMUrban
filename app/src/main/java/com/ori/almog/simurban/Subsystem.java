package com.ori.almog.simurban;

import java.util.ArrayList;

public abstract class Subsystem extends Communicator {

    protected ArrayList<Stimulus> stimuli;

    public Subsystem(){
        this.stimuli = new ArrayList<>();
    }

    abstract protected void destination(Stimulus s);
    abstract protected void compute();
}