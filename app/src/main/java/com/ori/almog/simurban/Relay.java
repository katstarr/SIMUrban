package com.ori.almog.simurban;

import java.util.ArrayList;

public class Relay extends Communicator {

    private ArrayList<Communicator> comms;

    public Relay(){
        super();
        this.name = "Generic relay";
    }

    protected void propagate(Stimulus s){

        switch(s.direction){

            case Up:
                this.comms = this.upRelays;
                break;

            case Down:
                this.comms = this.downRelays;
                break;

        }

        for (Communicator r : comms){
            if (r instanceof Relay){
                ((Relay)r).propagate(s);
            }
            else if (r instanceof Subsystem){
                ((Subsystem)r).destination(s);
            }
        }
    }
}
