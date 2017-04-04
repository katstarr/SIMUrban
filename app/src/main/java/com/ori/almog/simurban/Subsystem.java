package com.ori.almog.simurban;

import java.util.ArrayList;

public abstract class Subsystem extends Communicator {

    public Subsystem(){
        super();
        this.name = "Generic subsystem";
    }

    //This method means a stimulus s has arrived. You should figure out what to do with it!
    abstract protected void destination(Stimulus s);

    //This method occurs every clock cycle. Here you check for things like "is size > 50? if yes, upgrade!", etc.
    abstract protected void compute();
}